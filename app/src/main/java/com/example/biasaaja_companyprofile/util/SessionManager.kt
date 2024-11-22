package com.example.biasaaja_companyprofile.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveLoginState(username: String) { // Save the login state and username
        preferences.edit().putBoolean("is_logged_in", true).apply()
        preferences.edit().putString("username", username).apply()
    }

    fun isLoggedIn(): Boolean { // Check if the user is logged in
        return preferences.getBoolean("is_logged_in", false)
    }

    fun clearSession() { // Clear the login state and username
        preferences.edit().clear().apply()
    }
}