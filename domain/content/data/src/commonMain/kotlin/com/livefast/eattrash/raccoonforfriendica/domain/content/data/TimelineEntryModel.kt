package com.livefast.eattrash.raccoonforfriendica.domain.content.data

import kotlin.jvm.Transient

data class TimelineEntryModel(
    val attachments: List<AttachmentModel> = emptyList(),
    val bookmarked: Boolean = false,
    @Transient
    val bookmarkLoading: Boolean = false,
    val content: String,
    val created: String? = null,
    val creator: UserModel? = null,
    val edited: String? = null,
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
)

val TimelineEntryModel.isNsfw: Boolean get() = reblog?.sensitive ?: sensitive
