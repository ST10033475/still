package com.example.still

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("app_prefs", MODE_PRIVATE)
        val selectedLanguage = prefs.getString("selected_language", null)

        val context = if (selectedLanguage != null) {
            // Apply in-app selected language
            updateLocale(newBase, selectedLanguage)
        } else {
            // Use system app-specific language setting if in-app selection is not made
            newBase
        }
        super.attachBaseContext(context)
    }

    private fun updateLocale(context: Context, language: String): Context {
        val locale = when (language) {
            "English" -> Locale("en")
            "Zulu" -> Locale("zu")
            "Afrikaans" -> Locale("af")
            else -> Locale("en")
        }

        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}
