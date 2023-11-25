package org.sopt.dosopttemplate.presentation.signin

sealed class SignEvent {
    data class SignIn(val p: Unit) : SignEvent()
    data class NavigateSignUp(val p: Unit) : SignEvent()
    data class SignInSuccess(val p: Unit) : SignEvent()
}