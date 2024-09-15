package com.livefast.eattrash.raccoonforfriendica.domain.content.data

import kotlin.jvm.Transient
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

data class TimelineEntryModel(
    val attachments: List<AttachmentModel> = emptyList(),
    val bookmarked: Boolean = false,
    @Transient
    val bookmarkLoading: Boolean = false,
    val card: PreviewCardModel? = null,
    val content: String,
    val created: String? = null,
    val creator: UserModel? = null,
    val favorite: Boolean = false,
    @Transient
    val favoriteLoading: Boolean = false,
    val favoriteCount: Int = 0,
    val id: String,
    val inReplyTo: TimelineEntryModel? = null,
    val lang: String? = null,
    val parentId: String? = null,
    val pinned: Boolean = false,
    val reblog: TimelineEntryModel? = null,
    val reblogCount: Int = 0,
    val reblogged: Boolean = false,
    @Transient
    val reblogLoading: Boolean = false,
    val replyCount: Int = 0,
    val scheduled: String? = null,
    val sensitive: Boolean = false,
    val spoiler: String? = null,
    val tags: List<TagModel> = emptyList(),
    val title: String? = null,
    val updated: String? = null,
    val url: String? = null,
    val visibility: Visibility = Visibility.Public,
    @Transient
    val depth: Int = 0,
    @Transient
    val loadMoreButtonVisible: Boolean = false,
    val poll: PollModel? = null,
    @Transient
    val isSpoilerActive: Boolean = false,
)

val TimelineEntryModel.safeKey: String
    get() {
        fun StringBuilder.appendKeys(e: TimelineEntryModel) {
            append(e.id)
        }
        return buildString {
            appendKeys(this@safeKey)
            reblog?.run {
                append("--")
                appendKeys(this@run)
            }
        }
    }

val TimelineEntryModel.isNsfw: Boolean get() = reblog?.sensitive ?: sensitive

val Duration.isOldEntry: Boolean
    get() = this > 30.days
