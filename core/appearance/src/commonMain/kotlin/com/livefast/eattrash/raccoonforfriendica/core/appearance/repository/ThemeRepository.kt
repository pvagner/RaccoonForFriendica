package com.livefast.eattrash.raccoonforfriendica.core.appearance.repository

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.livefast.eattrash.raccoonforfriendica.core.appearance.data.UiFontFamily
import com.livefast.eattrash.raccoonforfriendica.core.appearance.data.UiTheme
import kotlinx.coroutines.flow.StateFlow

@Stable
interface ThemeRepository {
    val theme: StateFlow<UiTheme>
    val fontFamily: StateFlow<UiFontFamily>
    val dynamicColors: StateFlow<Boolean>
    val customSeedColor: StateFlow<Color?>

    fun changeTheme(theme: UiTheme)

    fun changeFontFamily(family: UiFontFamily)

    fun changeDynamicColors(enabled: Boolean)

    fun changeCustomSeedColor(color: Color?)

    fun getCommentBarColor(depth: Int): Color
}
