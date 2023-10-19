package org.sopt.dosopttemplate.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.domain.entity.UserEntity

@Parcelize
data class User(
    val id: String,
    val pwd: String,
    val sojuCount: String,
    val nickname: String
) : Parcelable {
    fun toUserEntity() = UserEntity(id, pwd, sojuCount, nickname)
}