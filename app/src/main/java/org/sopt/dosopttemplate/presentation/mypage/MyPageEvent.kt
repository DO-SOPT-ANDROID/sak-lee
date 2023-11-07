package org.sopt.dosopttemplate.presentation.mypage


sealed class MyPageEvent {
    data class LogOut(val p: Unit) : MyPageEvent()
}