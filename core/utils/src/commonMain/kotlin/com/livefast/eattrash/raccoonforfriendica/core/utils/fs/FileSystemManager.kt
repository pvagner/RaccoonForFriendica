package com.livefast.eattrash.raccoonforfriendica.core.utils.fs

import androidx.compose.runtime.Composable
import okio.Path

interface FileSystemManager {
    val isSupported: Boolean

    @Composable
    fun readFromFile(
        mimeTypes: Array<String>,
        callback: (String?) -> Unit,
    )

    @Composable
    fun writeToFile(
        mimeType: String,
        name: String,
        data: String,
        callback: (Boolean) -> Unit,
    )

    fun getTempDir(): Path
}

expect fun getFileSystemManager(): FileSystemManager