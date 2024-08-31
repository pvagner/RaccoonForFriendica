package com.livefast.eattrash.raccoonforfriendica.core.commonui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.Spacing
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.ancillaryTextAlpha
import com.livefast.eattrash.raccoonforfriendica.core.commonui.components.CustomImage
import com.livefast.eattrash.raccoonforfriendica.core.utils.imageload.toComposeImageBitmap

@Composable
fun SettingsImageInfo(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    title: String = "",
    url: String? = null,
    bytes: ByteArray? = null,
    onEdit: (() -> Unit)? = null,
) {
    val ancillaryColor = MaterialTheme.colorScheme.onBackground.copy(alpha = ancillaryTextAlpha)
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable {
                    onEdit?.invoke()
                }.padding(
                    vertical = Spacing.xs,
                    horizontal = Spacing.m,
                ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.xxs),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = ancillaryColor,
            )

            if (bytes != null && bytes.isNotEmpty()) {
                val imageBitmap = bytes.toComposeImageBitmap()
                Image(
                    modifier = imageModifier,
                    bitmap = imageBitmap,
                    contentDescription = null,
                    filterQuality = FilterQuality.Low,
                    contentScale = contentScale,
                )
            } else if (url.isNullOrEmpty()) {
                Box(
                    modifier = imageModifier,
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Default.FileOpen,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            } else {
                CustomImage(
                    modifier = imageModifier,
                    url = url,
                    quality = FilterQuality.Low,
                    contentScale = contentScale,
                )
            }
        }
    }
}