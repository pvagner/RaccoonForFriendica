package com.livefast.eattrash.raccoonforfriendica.core.utils.di

import com.livefast.eattrash.raccoonforfriendica.core.utils.calendar.CalendarHelper
import com.livefast.eattrash.raccoonforfriendica.core.utils.debug.AppInfoRepository
import com.livefast.eattrash.raccoonforfriendica.core.utils.debug.CrashReportManager
import com.livefast.eattrash.raccoonforfriendica.core.utils.gallery.GalleryHelper
import com.livefast.eattrash.raccoonforfriendica.core.utils.imageload.BlurHashRepository
import com.livefast.eattrash.raccoonforfriendica.core.utils.imageload.ImageLoaderProvider
import com.livefast.eattrash.raccoonforfriendica.core.utils.network.NetworkStateObserver
import com.livefast.eattrash.raccoonforfriendica.core.utils.share.ShareHelper
import org.koin.core.module.Module

expect fun getImageLoaderProvider(): ImageLoaderProvider

expect fun getGalleryHelper(): GalleryHelper

expect fun getShareHelper(): ShareHelper

expect fun getAppInfoRepository(): AppInfoRepository

expect fun getBlurHashRepository(): BlurHashRepository

expect fun getCrashReportManager(): CrashReportManager

expect fun getCalendarHelper(): CalendarHelper

expect fun getNetworkStateObserver(): NetworkStateObserver

expect val coreUtilsFileSystemModule: Module

expect val coreUtilsGalleryModule: Module

expect val coreUtilsShareModule: Module

expect val coreUtilsUrlModule: Module

expect val coreUtilsDebugModule: Module

expect val coreHapticFeedbackModule: Module

expect val coreCrashReportModule: Module

expect val coreUtilsCalendarModule: Module

expect val coreAppIconModule: Module
