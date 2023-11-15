package org.sopt.dosopttemplate.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestSignUpDto(
    @SerialName("username") val userName: String,
    @SerialName("nickname") val nickName: String,
    @SerialName("password") val passWord: String,
)