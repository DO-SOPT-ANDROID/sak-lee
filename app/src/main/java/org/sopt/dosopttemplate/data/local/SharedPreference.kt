package org.sopt.dosopttemplate.data.local

import org.sopt.dosopttemplate.domain.entity.UserEntity

interface SharedPreference {
    var user:UserEntity
    var checkLogin: Boolean
    fun clearPref()
}