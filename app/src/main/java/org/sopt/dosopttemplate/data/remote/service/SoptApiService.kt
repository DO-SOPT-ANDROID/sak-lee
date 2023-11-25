package org.sopt.dosopttemplate.data.remote.service

import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseUserInfoDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptApiService {
    @POST("api/v1/members")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto,
    )

    @POST("api/v1/members/sign-in")
    suspend fun signIn(
        @Body requestSignInDto: RequestSignInDto
    ): ResponseUserInfoDto
}