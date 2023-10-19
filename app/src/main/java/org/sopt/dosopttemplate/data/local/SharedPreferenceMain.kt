package org.sopt.dosopttemplate.data.local

import org.sopt.dosopttemplate.domain.entity.UserEntity

interface SharedPreferenceMain {
    var user:UserEntity
    var checkLogin: Boolean
    fun clearPref()
}