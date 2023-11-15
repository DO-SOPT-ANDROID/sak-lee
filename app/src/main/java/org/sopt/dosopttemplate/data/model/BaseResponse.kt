package org.sopt.dosopttemplate.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("message") val message: String,
    val data: T? = null
)