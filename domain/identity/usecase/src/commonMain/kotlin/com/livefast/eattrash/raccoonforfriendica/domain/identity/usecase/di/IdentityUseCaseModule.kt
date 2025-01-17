package com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.di

import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.ActiveAccountMonitor
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.ContentPreloadManager
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultActiveAccountMonitor
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultContentPreloadManager
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultDeleteAccountUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultEntryActionRepository
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultExportSettingsUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultImportSettingsUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultLoginUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultLogoutUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultSetupAccountUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DefaultSwitchAccountUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.DeleteAccountUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.EntryActionRepository
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.ExportSettingsUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.ImportSettingsUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.LoginUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.LogoutUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.SetupAccountUseCase
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.SwitchAccountUseCase
import org.koin.dsl.module

val domainIdentityUseCaseModule =
    module {
        single<SetupAccountUseCase> {
            DefaultSetupAccountUseCase(
                accountRepository = get(),
                settingsRepository = get(),
            )
        }

        single<LoginUseCase> {
            DefaultLoginUseCase(
                credentialsRepository = get(),
                apiConfigurationRepository = get(),
                accountRepository = get(),
                settingsRepository = get(),
                accountCredentialsCache = get(),
                supportedFeatureRepository = get(),
            )
        }

        single<LogoutUseCase> {
            DefaultLogoutUseCase(
                apiConfigurationRepository = get(),
                accountRepository = get(),
            )
        }
        single<SwitchAccountUseCase> {
            DefaultSwitchAccountUseCase(
                accountRepository = get(),
            )
        }
        single<DeleteAccountUseCase> {
            DefaultDeleteAccountUseCase(
                accountRepository = get(),
                settingsRepository = get(),
                accountCredentialsCache = get(),
            )
        }
        single<ActiveAccountMonitor> {
            DefaultActiveAccountMonitor(
                accountRepository = get(),
                apiConfigurationRepository = get(),
                identityRepository = get(),
                settingsRepository = get(),
                accountCredentialsCache = get(),
                supportedFeatureRepository = get(),
                contentPreloadManager = get(),
                markerRepository = get(),
                notificationCoordinator = get(),
                announcementsManager = get(),
            )
        }
        single<EntryActionRepository> {
            DefaultEntryActionRepository(
                identityRepository = get(),
                supportedFeatureRepository = get(),
            )
        }
        single<ContentPreloadManager> {
            DefaultContentPreloadManager(
                timelineEntryRepository = get(),
                trendingRepository = get(),
                notificationRepository = get(),
            )
        }
        single<ImportSettingsUseCase> {
            DefaultImportSettingsUseCase(
                settingsRepository = get(),
            )
        }
        single<ExportSettingsUseCase> {
            DefaultExportSettingsUseCase(
                settingsRepository = get(),
            )
        }
    }
