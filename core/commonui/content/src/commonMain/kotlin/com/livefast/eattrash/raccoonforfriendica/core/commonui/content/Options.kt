package com.livefast.eattrash.raccoonforfriendica.core.commonui.content

import androidx.compose.runtime.Composable
import com.livefast.eattrash.raccoonforfriendica.core.l10n.messages.LocalStrings

sealed interface OptionId {
    data object Edit : OptionId

    data object Delete : OptionId

    data object Share : OptionId

    data object CopyUrl : OptionId

    data object Mute : OptionId

    data object Unmute : OptionId

    data object Block : OptionId

    data object Unblock : OptionId

    data object Pin : OptionId

    data object Unpin : OptionId

    data object Move : OptionId
}

@Composable
private fun OptionId.toReadableName(): String =
    when (this) {
        OptionId.Edit -> LocalStrings.current.actionEdit
        OptionId.Delete -> LocalStrings.current.actionDelete
        OptionId.Share -> LocalStrings.current.actionShare
        OptionId.CopyUrl -> LocalStrings.current.actionCopyUrl
        OptionId.Mute -> LocalStrings.current.actionMute
        OptionId.Unmute -> LocalStrings.current.actionUnmute
        OptionId.Block -> LocalStrings.current.actionBlock
        OptionId.Unblock -> LocalStrings.current.actionUnblock
        OptionId.Pin -> LocalStrings.current.actionPin
        OptionId.Unpin -> LocalStrings.current.actionUnpin
        OptionId.Move -> LocalStrings.current.actionMove
    }

@Composable
fun OptionId.toOption() = Option(id = this, label = toReadableName())

data class Option(
    val id: OptionId,
    val label: String,
)
