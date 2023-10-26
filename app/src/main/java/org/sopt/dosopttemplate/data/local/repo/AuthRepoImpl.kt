package org.sopt.dosopttemplate.data.local.repo

import org.sopt.dosopttemplate.data.local.SharedPreferenceImpl
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val sharedPreferenceImpl: SharedPreferenceImpl
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

}