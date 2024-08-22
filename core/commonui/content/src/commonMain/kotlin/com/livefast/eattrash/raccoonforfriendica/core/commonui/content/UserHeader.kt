package com.livefast.eattrash.raccoonforfriendica.core.commonui.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.IconSize
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.Spacing
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.ancillaryTextAlpha
import com.livefast.eattrash.raccoonforfriendica.core.commonui.components.CustomImage
import com.livefast.eattrash.raccoonforfriendica.core.commonui.components.PlaceholderImage
import com.livefast.eattrash.raccoonforfriendica.core.l10n.messages.LocalStrings
import com.livefast.eattrash.raccoonforfriendica.core.utils.ellipsize
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.NotificationStatusNextAction
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.RelationshipStatusNextAction
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.UserModel

@Composable
fun UserHeader(
    user: UserModel?,
    modifier: Modifier = Modifier,
    onOpenImage: ((String) -> Unit)? = null,
    onOpenFollowers: (() -> Unit)? = null,
    onOpenFollowing: (() -> Unit)? = null,
    onOpenUrl: ((String) -> Unit)? = null,
    onRelationshipClicked: ((RelationshipStatusNextAction) -> Unit)? = null,
    onNotificationsClicked: ((NotificationStatusNextAction) -> Unit)? = null,
    onOpenInForumMode: (() -> Unit)? = null,
) {
    val banner = user?.header.orEmpty()
    val avatar = user?.avatar.orEmpty()
    val avatarSize = 110.dp
    val fullColor = MaterialTheme.colorScheme.onBackground
    val ancillaryColor = MaterialTheme.colorScheme.onBackground.copy(ancillaryTextAlpha)
    val relationshipStatus = user?.relationshipStatus
    val notificationStatus = user?.notificationStatus

    Column(
        modifier = modifier,
    ) {
        Box {
            CustomImage(
                modifier =
                    Modifier
                        .padding(bottom = avatarSize * 0.8f)
                        .aspectRatio(16 / 9f)
                        .clickable {
                            if (banner.isNotBlank()) {
                                onOpenImage?.invoke(banner)
                            }
                        },
                url = banner,
                contentScale = ContentScale.FillBounds,
            )

            Row(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .offset(
                            y = -avatarSize * 0.1f,
                        ).padding(horizontal = Spacing.m),
                verticalAlignment = Alignment.Bottom,
            ) {
                if (avatar.isNotEmpty()) {
                    CustomImage(
                        modifier =
                            Modifier
                                .size(avatarSize)
                                .clip(RoundedCornerShape(avatarSize / 2))
                                .clickable {
                                    onOpenImage?.invoke(avatar)
                                },
                        url = avatar,
                        quality = FilterQuality.Low,
                        contentScale = ContentScale.FillBounds,
                    )
                } else {
                    PlaceholderImage(
                        size = avatarSize,
                        title = user?.displayName ?: user?.handle ?: "?",
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    verticalArrangement = Arrangement.spacedBy(Spacing.xs),
                    horizontalAlignment = Alignment.End,
                ) {
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val followers = user?.followers ?: 0
                        val following = user?.following ?: 0
                        Text(
                            modifier = Modifier.clickable { onOpenFollowers?.invoke() },
                            text =
                                buildString {
                                    append(followers)
                                    append(" ")
                                    append(LocalStrings.current.accountFollower(followers))
                                },
                            style = MaterialTheme.typography.labelMedium,
                            color = ancillaryColor,
                        )
                        Text(
                            text = " • ",
                            style = MaterialTheme.typography.labelMedium,
                            color = ancillaryColor,
                        )
                        Text(
                            modifier = Modifier.clickable { onOpenFollowing?.invoke() },
                            text =
                                buildString {
                                    append(following)
                                    append(" ")
                                    append(LocalStrings.current.accountFollowing(following))
                                },
                            style = MaterialTheme.typography.labelMedium,
                            color = ancillaryColor,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.m),
                    ) {
                        if (notificationStatus != null) {
                            UserNotificationButton(
                                status = notificationStatus,
                                pending = user.notificationStatusPending,
                                onClick = onNotificationsClicked,
                            )
                        }
                        if (relationshipStatus != null) {
                            UserRelationshipButton(
                                status = relationshipStatus,
                                pending = user.relationshipStatusPending,
                                onClick = onRelationshipClicked,
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = Spacing.m),
            verticalArrangement = Arrangement.spacedBy(Spacing.xs),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Spacing.xs),
                ) {
                    Text(
                        text = user?.displayName ?: user?.username ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        color = fullColor,
                    )
                    Text(
                        text = (user?.handle ?: user?.username ?: "").ellipsize(25),
                        style = MaterialTheme.typography.titleMedium,
                        color = ancillaryColor,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (user?.group == true) {
                    Row(
                        modifier =
                            Modifier.clickable {
                                onOpenInForumMode?.invoke()
                            },
                        horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = LocalStrings.current.actionOpenInForumMode,
                            style = MaterialTheme.typography.labelMedium,
                            color = ancillaryColor,
                        )
                        Icon(
                            modifier = Modifier.size(IconSize.s),
                            imageVector = Icons.AutoMirrored.Default.OpenInNew,
                            contentDescription = null,
                            tint = ancillaryColor,
                        )
                    }
                }
            }
            user?.bio?.takeIf { it.isNotEmpty() }?.let { bio ->
                ContentBody(
                    content = bio,
                    onOpenUrl = onOpenUrl,
                )
            }
        }
    }
}
