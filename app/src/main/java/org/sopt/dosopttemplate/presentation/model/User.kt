package org.sopt.dosopttemplate.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val pwd: String,
    val sojuCount: String,
    val nickname: String
) : Parcelable