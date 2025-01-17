package com.livefast.eattrash.raccoonforfriendica.feature.unpublished.di

import com.livefast.eattrash.raccoonforfriendica.feature.unpublished.UnpublishedMviModel
import com.livefast.eattrash.raccoonforfriendica.feature.unpublished.UnpublishedViewModel
import org.koin.dsl.module

val featureUnpublishedModule =
    module {
        factory<UnpublishedMviModel> {
            UnpublishedViewModel(
                paginationManager = get(),
                identityRepository = get(),
                settingsRepository = get(),
                scheduledEntryRepository = get(),
                draftRepository = get(),
                notificationCenter = get(),
                imagePreloadManager = get(),
                blurHashRepository = get(),
                imageAutoloadObserver = get(),
            )
        }
    }
