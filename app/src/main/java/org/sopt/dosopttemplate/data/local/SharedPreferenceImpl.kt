package org.sopt.dosopttemplate.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import org.sopt.dosopttemplate.domain.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceImpl @Inject constructor(
    private val prefs: SharedPreferences,
) : SharedPreference {

    private val gson = GsonBuilder().create()

    override var user: UserEntity
        get() = gson.fromJson(prefs.getString(USER, null), UserEntity::class.java)
        set(value) = prefs.edit { putString(USER, gson.toJson(value)) }

    override var checkLogin: Boolean
        get() = prefs.getBoolean(CHECKLOGIN, false)
        set(value) = prefs.edit { putBoolean(CHECKLOGIN, value) }

    override fun clearPref() = prefs.edit { clear() }

    companion object {
        const val CHECKLOGIN = "CheckLogin"
        const val USER = "User"
    }
}
