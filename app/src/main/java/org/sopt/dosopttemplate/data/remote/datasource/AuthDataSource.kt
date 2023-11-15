package org.sopt.dosopttemplate.data.remote.datasource

import org.sopt.dosopttemplate.data.model.BaseResponse
import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseUserInfoDto
import org.sopt.dosopttemplate.data.remote.service.SoptApiService
import javax.inject.Inject

class AuthDataSource@Inject constructor(
private val authService: SoptApiService,
) {
    suspend fun signUp(
        requestPostSignUpDto: RequestSignUpDto,
    ): Unit =
        authService.signUp(requestPostSignUpDto)

    suspend fun signIn(
        requestPostSignInDto: RequestSignInDto,
    ): ResponseUserInfoDto =
        authService.signIn(requestPostSignInDto)
}