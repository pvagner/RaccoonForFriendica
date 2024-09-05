package com.livefast.eattrash.raccoonforfriendica.core.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstanceRule(
    @SerialName("id") val id: String? = null,
    @SerialName("text") val text: String? = null,
)