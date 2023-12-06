package org.sopt.dosopttemplate.presentation.model

data class LoginFormState(
    val idError: String? = null,
    val pwError: String? = null,
    val isDataValid: Boolean = false
)
