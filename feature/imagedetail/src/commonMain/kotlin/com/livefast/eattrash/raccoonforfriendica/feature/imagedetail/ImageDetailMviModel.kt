package com.livefast.eattrash.raccoonforfriendica.feature.imagedetail

import androidx.compose.runtime.Stable
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.model.ScreenModel
import com.livefast.eattrash.raccoonforfriendica.core.architecture.MviModel

@Stable
interface ImageDetailMviModel :
    ScreenModel,
    MviModel<ImageDetailMviModel.Intent, ImageDetailMviModel.UiState, ImageDetailMviModel.Effect> {
    sealed interface Intent {
        data object SaveToGallery : Intent

        data class ChangeContentScale(
            val contentScale: ContentScale,
        ) : Intent

        data object ShareAsUrl : Intent

        data object ShareAsFile : Intent
    }

    data class UiState(
        val loading: Boolean = false,
        val contentScale: ContentScale = ContentScale.FillWidth,
    )

    sealed interface Effect {
        data object ShareSuccess : Effect

        data object ShareFailure : Effect
    }
}
