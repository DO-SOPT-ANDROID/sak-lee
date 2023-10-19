package org.sopt.dosopttemplate.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.sopt.dosopttemplate.domain.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreference @Inject constructor(
    private val prefs: SharedPreferences,
) : SharedPreferenceMain {

    private val userGson: Gson = GsonBuilder().create()

    override var user: UserEntity
        get() = userGson.fromJson(prefs.getString("User", null), UserEntity::class.java)
        set(value) = prefs.edit { putString("User", userGson.toJson(value)) }

    override var checkLogin: Boolean
        get() = prefs.getBoolean("CheckLogin", false)
        set(value) = prefs.edit { putBoolean("CheckLogin", value) }

    override fun clearPref() = prefs.edit { clear() }
}
