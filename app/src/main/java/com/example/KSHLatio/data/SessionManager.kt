@file:Suppress("PackageName")

package com.example.KSHLatio.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var isLogin: Boolean
        get() = pref.getBoolean(KEY_IS_LOGIN, false)
        set(value) = pref.edit().putBoolean(KEY_IS_LOGIN, value).apply()

    var username: String?
        get() = pref.getString(KEY_USERNAME, "")
        set(value) = pref.edit().putString(KEY_USERNAME, value).apply()

    var role: String?
        get() = pref.getString(KEY_ROLE, "Petani")
        set(value) = pref.edit().putString(KEY_ROLE, value).apply()

    var isDarkMode: Boolean
        get() = pref.getBoolean(KEY_DARK_MODE, false)
        set(value) = pref.edit().putBoolean(KEY_DARK_MODE, value).apply()

    fun clearSession() {
        pref.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "user_pref"
        private const val KEY_IS_LOGIN = "isLoggedIn"
        private const val KEY_USERNAME = "username"
        private const val KEY_ROLE = "role"
        private const val KEY_DARK_MODE = "isDarkMode"
    }
}