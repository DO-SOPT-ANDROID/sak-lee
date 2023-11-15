package org.sopt.dosopttemplate.data.local.repo

import android.util.Log
import org.sopt.dosopttemplate.data.local.SharedPreferenceImpl
import org.sopt.dosopttemplate.data.model.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.response.ResponseUserInfoDto
import org.sopt.dosopttemplate.data.remote.datasource.AuthDataSource
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val sharedPreferenceImpl: SharedPreferenceImpl,
    private val authDataSource: AuthDataSource
) : AuthRepo {
    override fun getUser(): UserEntity = sharedPreferenceImpl.user
    override fun saveUser(userEntity: UserEntity) {
        sharedPreferenceImpl.user = userEntity
    }

    override fun checkLogin() = sharedPreferenceImpl.checkLogin
    override fun saveCheckLogin(checkLogin: Boolean) {
        sharedPreferenceImpl.checkLogin = checkLogin
    }

    override fun clear() {
        sharedPreferenceImpl.clearPref()
    }

    override suspend fun signUp(requestSignUpDto: RequestSignUpDto): Result<Unit> =
        runCatching {
            authDataSource.signUp(requestSignUpDto)
        }

    override suspend fun signIn(requestSignInDto: RequestSignInDto): Result<ResponseUserInfoDto?> =
        runCatching { authDataSource.signIn(requestSignInDto) }

}