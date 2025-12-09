package com.seguridad.seguridadinformatica.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object AuthManager {

    private fun prefs(context: Context): EncryptedSharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build();

        return EncryptedSharedPreferences.create(
            context,
            "auth_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    fun saveAccessToken(context: Context, token: String){
        prefs(context).edit().putString("access_token", token).apply()
    }

    fun getAccessToken(context: Context) : String? {
        return prefs(context).getString("access_token", null)
    }

    fun logout(context: Context) {
        prefs(context).edit().clear().apply()
    }

}