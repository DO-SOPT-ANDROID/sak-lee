package org.sopt.dosopttemplate.presentation.main


sealed class MainEvent {
    data class LogOut(val p: Unit) : MainEvent()
}