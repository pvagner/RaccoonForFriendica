package com.livefast.eattrash.raccoonforfriendica.feature.thread

import cafe.adriel.voyager.core.model.screenModelScope
import com.livefast.eattrash.raccoonforfriendica.core.architecture.DefaultMviModel
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.TimelineEntryModel
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.TimelineEntryRepository
import com.livefast.eattrash.raccoonforfriendica.feature.thread.usecase.PopulateThreadUseCase
import kotlinx.coroutines.launch

class ThreadViewModel(
    private val entryId: String,
    private val populateThreadUseCase: PopulateThreadUseCase,
    private val timelineEntryRepository: TimelineEntryRepository,
) : DefaultMviModel<ThreadMviModel.Intent, ThreadMviModel.State, ThreadMviModel.Effect>(
        initialState = ThreadMviModel.State(),
    ),
    ThreadMviModel {
    init {
        screenModelScope.launch {
            if (uiState.value.initial) {
                refresh(initial = true)
            }
        }
    }

    override fun reduce(intent: ThreadMviModel.Intent) {
        when (intent) {
            ThreadMviModel.Intent.Refresh ->
                screenModelScope.launch {
                    refresh()
                }

            is ThreadMviModel.Intent.LoadMoreReplies ->
                screenModelScope.launch {
                    loadMoreReplies(intent.entry)
                }

            is ThreadMviModel.Intent.ToggleBookmark -> toggleBookmark(intent.entry)
            is ThreadMviModel.Intent.ToggleFavorite -> toggleFavorite(intent.entry)
            is ThreadMviModel.Intent.ToggleReblog -> toggleReblog(intent.entry)
        }
    }

    private suspend fun refresh(initial: Boolean = false) {
        updateState {
            it.copy(
                initial = initial,
                loading = true,
                refreshing = !initial,
                canFetchMore = true,
            )
        }
        val result = populateThreadUseCase(entryId)
        val originalEntry = result.firstOrNull { it.id == entryId }
        val replies = result.filter { it.id != entryId }
        updateState {
            it.copy(
                entry = originalEntry,
                replies = replies,
                loading = false,
                initial = false,
                refreshing = false,
                canFetchMore = false,
            )
        }
    }

    private suspend fun loadMoreReplies(entry: TimelineEntryModel) {
        val result = populateThreadUseCase(entry.id)
        val newReplies = result.filter { it.id != entry.id }
        if (newReplies.isEmpty()) {
            // abort and disable load more button
            updateEntryInState(entry.id) { it.copy(loadMoreButtonVisible = false) }
        } else {
            val replies = uiState.value.replies.toMutableList()
            val insertIndex = replies.indexOfFirst { it.id == entry.id }
            replies[insertIndex] = replies[insertIndex].copy(loadMoreButtonVisible = false)
            replies.addAll(index = insertIndex + 1, newReplies)
            updateState {
                it.copy(replies = replies)
            }
        }
    }

    private suspend fun updateEntryInState(
        entryId: String,
        block: (TimelineEntryModel) -> TimelineEntryModel,
    ) {
        val currentMainEntry = uiState.value.entry
        when (entryId) {
            currentMainEntry?.id -> {
                updateState {
                    it.copy(entry = currentMainEntry.let(block))
                }
            }

            currentMainEntry?.reblog?.id -> {
                updateState {
                    it.copy(
                        entry = currentMainEntry.copy(reblog = currentMainEntry.reblog?.let(block)),
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        replies =
                            it.replies.map { entry ->
                                when {
                                    entry.id == entryId -> {
                                        entry.let(block)
                                    }

                                    entry.reblog?.id == entryId -> {
                                        entry.copy(reblog = entry.reblog?.let(block))
                                    }

                                    else -> {
                                        entry
                                    }
                                }
                            },
                    )
                }
            }
        }
    }

    private fun toggleReblog(entry: TimelineEntryModel) {
        screenModelScope.launch {
            updateEntryInState(entry.id) {
                it.copy(
                    reblogLoading = true,
                )
            }
            val newEntry =
                if (entry.reblogged) {
                    timelineEntryRepository.unreblog(entry.id)
                } else {
                    timelineEntryRepository.reblog(entry.id)
                }
            if (newEntry != null) {
                updateEntryInState(entry.id) {
                    it.copy(
                        reblogged = newEntry.reblogged,
                        reblogCount = newEntry.reblogCount,
                        reblogLoading = false,
                    )
                }
            } else {
                updateEntryInState(entry.id) {
                    it.copy(
                        reblogLoading = false,
                    )
                }
            }
        }
    }

    private fun toggleFavorite(entry: TimelineEntryModel) {
        screenModelScope.launch {
            updateEntryInState(entry.id) {
                it.copy(
                    favoriteLoading = true,
                )
            }
            val newEntry =
                if (entry.favorite) {
                    timelineEntryRepository.unfavorite(entry.id)
                } else {
                    timelineEntryRepository.favorite(entry.id)
                }
            if (newEntry != null) {
                updateEntryInState(entry.id) {
                    it.copy(
                        favorite = newEntry.favorite,
                        favoriteCount = newEntry.favoriteCount,
                        favoriteLoading = false,
                    )
                }
            } else {
                updateEntryInState(entry.id) {
                    it.copy(
                        favoriteLoading = false,
                    )
                }
            }
        }
    }

    private fun toggleBookmark(entry: TimelineEntryModel) {
        screenModelScope.launch {
            updateEntryInState(entry.id) {
                it.copy(
                    bookmarkLoading = true,
                )
            }
            val newEntry =
                if (entry.bookmarked) {
                    timelineEntryRepository.unbookmark(entry.id)
                } else {
                    timelineEntryRepository.bookmark(entry.id)
                }
            if (newEntry != null) {
                updateEntryInState(entry.id) {
                    it.copy(
                        bookmarked = newEntry.bookmarked,
                        bookmarkLoading = false,
                    )
                }
            } else {
                updateEntryInState(entry.id) {
                    it.copy(
                        bookmarkLoading = false,
                    )
                }
            }
        }
    }
}