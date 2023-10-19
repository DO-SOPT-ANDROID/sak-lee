package org.sopt.dosopttemplate.domain.repo

import org.sopt.dosopttemplate.domain.entity.UserEntity

interface AuthRepo {

    fun getUser(): UserEntity

    fun saveUser(userEntity: UserEntity)

    fun checkLogin(): Boolean

    fun saveCheckLogin(checkLogin: Boolean)

    fun clear()
}