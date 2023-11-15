package org.sopt.dosopttemplate.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto(
    @SerialName("id") val id: Long,
    @SerialName("username") val userName: String,
    @SerialName("nickname") val nickName: String
)