package org.sopt.dosopttemplate.domain.repo

import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseUserInfoDto
import org.sopt.dosopttemplate.domain.entity.UserEntity

interface AuthRepo {

    fun getUser(): UserEntity

    fun saveUser(userEntity: UserEntity)

    fun checkLogin(): Boolean

    fun saveCheckLogin(checkLogin: Boolean)

    fun clear()

    suspend fun signUp(requestSignUpDto: RequestSignUpDto):Result<Unit>
    suspend fun signIn(requestSignInDto: RequestSignInDto):Result<ResponseUserInfoDto?>
}