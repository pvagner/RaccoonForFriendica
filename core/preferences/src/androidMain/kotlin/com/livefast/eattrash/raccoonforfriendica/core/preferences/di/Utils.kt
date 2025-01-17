package com.livefast.eattrash.raccoonforfriendica.core.preferences.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.livefast.eattrash.raccoonforfriendica.core.preferences.DefaultSettingsWrapper
import com.livefast.eattrash.raccoonforfriendica.core.preferences.SettingsWrapper
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

internal actual val nativePreferencesModule =
    module {
        single { params ->
            SharedPreferencesProvider(
                name = params[0],
                context = get(),
            )
        }
        single<Settings> { params ->
            val sharedPreferencesProvider: SharedPreferencesProvider =
                get(
                    parameters = { parametersOf(params[0]) },
                )
            SharedPreferencesSettings(
                delegate = sharedPreferencesProvider.sharedPreferences,
                commit = false,
            )
        }
        single<SettingsWrapper> {
            DefaultSettingsWrapper(settings = get(parameters = { parametersOf(PREFERENCES_NAME) }))
        }
    }

private const val PREFERENCES_NAME = "secret_shared_prefs"

private class SharedPreferencesProvider(
    name: String,
    context: Context,
) {
    private val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    val sharedPreferences: SharedPreferences =
        EncryptedSharedPreferences.create(
            name,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
}
