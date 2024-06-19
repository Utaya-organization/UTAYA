package com.dicoding.utaya.data.utils

import android.content.Context
import android.content.SharedPreferences

object Preference {

    private const val PREF_NAME = "onSignIn"
    private const val TOKEN_KEY = "token"
    private const val USERNAME_KEY = "username"

    fun initPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String, context: Context) {
        val editor = initPref(context).edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun saveUsername(username: String, context: Context) {
        val editor = initPref(context).edit()
        editor.putString(USERNAME_KEY, username)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return initPref(context).getString(TOKEN_KEY, null)
    }

    fun getUsername(context: Context): String? {
        return initPref(context).getString(USERNAME_KEY, null)
    }

    fun logOut(context: Context) {
        val editor = initPref(context).edit()
        editor.clear()
        editor.apply()
    }
}