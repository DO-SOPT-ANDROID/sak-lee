package org.sopt.dosopttemplate.presentation.model

sealed class HomeList {
    abstract val userId: String
}

data class MyProfile(
    val picture: String,
    val name: String,
    override val userId: String
) : HomeList()

data class OtherProfile(
    val picture: String,
    val name: String,
    override val userId: String
) : HomeList()
