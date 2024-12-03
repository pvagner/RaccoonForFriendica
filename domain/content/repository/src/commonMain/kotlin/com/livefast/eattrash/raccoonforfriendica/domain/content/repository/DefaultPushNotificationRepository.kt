package com.livefast.eattrash.raccoonforfriendica.domain.content.repository

import com.livefast.eattrash.raccoonforfriendica.core.api.provider.ServiceProvider
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.NotificationPolicy
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.NotificationType
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.utils.toDto
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.utils.toRawValue
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
internal class DefaultPushNotificationRepository(
    @Named("default") private val provider: ServiceProvider,
) : PushNotificationRepository {
    override suspend fun create(
        endpoint: String,
        pubKey: String,
        auth: String,
        types: List<NotificationType>,
        policy: NotificationPolicy,
    ): String? =
        withContext(Dispatchers.IO) {
            runCatching {
                val data =
                    FormDataContent(
                        parameters {
                            append("subscription[endpoint]", endpoint)
                            append("subscription[keys][p256dh]", pubKey)
                            append("subscription[keys][auth]", auth)
                            for (type in NotificationType.ALL) {
                                append(
                                    "data[alerts][${type.toRawValue()}]",
                                    (type in types).toString(),
                                )
                            }
                            append("data[policy]", policy.toDto())
                        },
                    )
                provider.push.create(data)?.serverKey
            }.getOrNull()
        }

    override suspend fun update(
        types: List<NotificationType>,
        policy: NotificationPolicy,
    ): String? =
        withContext(Dispatchers.IO) {
            runCatching {
                val data =
                    FormDataContent(
                        parameters {
                            for (type in NotificationType.ALL) {
                                append(
                                    "data[alerts][${type.toRawValue()}]",
                                    (type in types).toString(),
                                )
                            }
                            append("data[policy]", policy.toDto())
                        },
                    )
                provider.push.update(data)?.serverKey
            }.getOrNull()
        }

    override suspend fun delete(): Boolean =
        withContext(Dispatchers.IO) {
            runCatching {
                provider.push.delete()
                true
            }.getOrElse { false }
        }
}
