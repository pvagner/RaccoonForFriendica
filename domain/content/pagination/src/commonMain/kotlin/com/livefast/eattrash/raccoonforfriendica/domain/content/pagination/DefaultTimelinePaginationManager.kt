package com.livefast.eattrash.raccoonforfriendica.domain.content.pagination

import com.livefast.eattrash.raccoonforfriendica.core.notifications.NotificationCenter
import com.livefast.eattrash.raccoonforfriendica.core.notifications.events.TimelineEntryDeletedEvent
import com.livefast.eattrash.raccoonforfriendica.core.notifications.events.TimelineEntryUpdatedEvent
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.TimelineEntryModel
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.TimelineType
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.isNsfw
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.safeKey
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.EmojiHelper
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.ReplyHelper
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.TimelineEntryRepository
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.TimelineRepository
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.utils.ListWithPageCursor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class DefaultTimelinePaginationManager(
    private val timelineRepository: TimelineRepository,
    private val timelineEntryRepository: TimelineEntryRepository,
    private val emojiHelper: EmojiHelper,
    private val replyHelper: ReplyHelper,
    notificationCenter: NotificationCenter,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : TimelinePaginationManager {
    private var specification: TimelinePaginationSpecification? = null
    private var pageCursor: String? = null
    override var canFetchMore: Boolean = true
    override val history = mutableListOf<TimelineEntryModel>()
    private val mutex = Mutex()
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    init {
        notificationCenter
            .subscribe(TimelineEntryUpdatedEvent::class)
            .onEach { event ->
                mutex.withLock {
                    val idx = history.indexOfFirst { e -> e.id == event.entry.id }
                    if (idx >= 0) {
                        history[idx] = event.entry
                    }
                }
            }.launchIn(scope)
        notificationCenter
            .subscribe(TimelineEntryDeletedEvent::class)
            .onEach { event ->
                mutex.withLock {
                    val idx = history.indexOfFirst { e -> e.id == event.id }
                    if (idx >= 0) {
                        history.removeAt(idx)
                    }
                }
            }.launchIn(scope)
    }

    override suspend fun reset(specification: TimelinePaginationSpecification) {
        this.specification = specification
        pageCursor = null
        mutex.withLock {
            history.clear()
        }
        canFetchMore = true
    }

    override suspend fun restoreHistory(values: List<TimelineEntryModel>) {
        mutex.withLock {
            history.clear()
            history.addAll(values)
            canFetchMore = true
        }
    }

    override suspend fun loadNextPage(): List<TimelineEntryModel> {
        val specification = this.specification ?: return emptyList()

        val results =
            when (specification) {
                is TimelinePaginationSpecification.Feed -> {
                    when (specification.timelineType) {
                        is TimelineType.All ->
                            timelineRepository.getPublic(
                                pageCursor = pageCursor,
                                refresh = specification.refresh,
                            )

                        is TimelineType.Subscriptions ->
                            timelineRepository.getHome(
                                pageCursor = pageCursor,
                                refresh = specification.refresh,
                            )

                        is TimelineType.Local ->
                            timelineRepository.getLocal(
                                pageCursor = pageCursor,
                                refresh = specification.refresh,
                            )

                        is TimelineType.Circle ->
                            timelineRepository.getCircle(
                                id =
                                    specification.timelineType.circle
                                        ?.id
                                        .orEmpty(),
                                pageCursor = pageCursor,
                            )
                    }?.updatePaginationData()
                        ?.filterReplies(included = !specification.excludeReplies)
                        ?.filterNsfw(specification.includeNsfw)
                        ?.deduplicate()
                        ?.fixupCreatorEmojis()
                        ?.fixupInReplyTo()
                        .orEmpty()
                }

                is TimelinePaginationSpecification.Hashtag -> {
                    timelineRepository
                        .getHashtag(
                            hashtag = specification.hashtag,
                            pageCursor = pageCursor,
                        )?.updatePaginationData()
                        ?.filterNsfw(specification.includeNsfw)
                        ?.deduplicate()
                        ?.fixupCreatorEmojis()
                        ?.fixupInReplyTo()
                        .orEmpty()
                }

                is TimelinePaginationSpecification.User ->
                    timelineEntryRepository
                        .getByUser(
                            userId = specification.userId,
                            pageCursor = pageCursor,
                            excludeReplies = specification.excludeReplies,
                            excludeReblogs = specification.excludeReblogs,
                            onlyMedia = specification.onlyMedia,
                            pinned = specification.pinned,
                            enableCache = specification.enableCache,
                            refresh = specification.refresh,
                        )
                        // intended: there is a bug in user post pagination
                        ?.deduplicate()
                        ?.updatePaginationData()
                        ?.filterNsfw(specification.includeNsfw)
                        ?.fixupCreatorEmojis()
                        ?.fixupInReplyTo()
                        .orEmpty()

                is TimelinePaginationSpecification.Forum ->
                    timelineEntryRepository
                        .getByUser(
                            userId = specification.userId,
                            pageCursor = pageCursor,
                            excludeReplies = true,
                        )?.updatePaginationData()
                        ?.filterNsfw(specification.includeNsfw)
                        ?.filter { it.reblog != null && it.reblog?.inReplyTo == null }
                        ?.deduplicate()
                        ?.fixupCreatorEmojis()
                        .orEmpty()
            }
        mutex.withLock {
            history.addAll(results)
        }

        // return a copy
        return history.map { it }
    }

    private fun List<TimelineEntryModel>.updatePaginationData(): List<TimelineEntryModel> =
        apply {
            lastOrNull()?.also {
                pageCursor = it.id
            }
            canFetchMore = isNotEmpty()
        }

    private fun ListWithPageCursor<TimelineEntryModel>.updatePaginationData(): List<TimelineEntryModel> =
        run {
            pageCursor = cursor
            canFetchMore = list.isNotEmpty()
            list
        }

    private fun List<TimelineEntryModel>.deduplicate(): List<TimelineEntryModel> =
        filter { e1 ->
            history.none { e2 -> e1.id == e2.id }
        }.distinctBy { it.safeKey }

    private fun List<TimelineEntryModel>.filterReplies(included: Boolean): List<TimelineEntryModel> =
        filter {
            included || it.inReplyTo == null
        }

    private fun List<TimelineEntryModel>.filterNsfw(included: Boolean): List<TimelineEntryModel> = filter { included || !it.isNsfw }

    private suspend fun List<TimelineEntryModel>.fixupCreatorEmojis(): List<TimelineEntryModel> =
        with(emojiHelper) {
            map {
                it.withEmojisIfMissing()
            }
        }

    private suspend fun List<TimelineEntryModel>.fixupInReplyTo(): List<TimelineEntryModel> =
        with(replyHelper) {
            map {
                it.withInReplyToIfMissing()
            }
        }
}
