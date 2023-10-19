package org.sopt.dosopttemplate.data.local.repo

import org.sopt.dosopttemplate.data.local.SharedPreference
import org.sopt.dosopttemplate.domain.entity.UserEntity
import org.sopt.dosopttemplate.domain.repo.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val sharedPreference: SharedPreference
) : AuthRepo {

    override fun getUser(): UserEntity = sharedPreference.user

    override fun saveUser(userEntity: UserEntity) {
        sharedPreference.user = userEntity
    }

    override fun checkLogin() = sharedPreference.checkLogin
    override fun saveCheckLogin(checkLogin: Boolean) {
        sharedPreference.checkLogin = checkLogin
    }

    override fun clear() {
        sharedPreference.clearPref()
    }

}