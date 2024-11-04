package com.example.still

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.res.Configuration
import android.os.LocaleList
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import org.intellij.lang.annotations.Language
import java.util.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
//Variables are  created to handle the langauge selections
        val languageSelector = findViewById<TextView>(R.id.language_selector)
        val languages = resources.getStringArray(R.array.languages)
        val currentLanguage = getCurrentLanguage()

        languageSelector.text = currentLanguage

        languageSelector.setOnClickListener{
           val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Language")
            builder.setItems(languages) {_, which ->
                val selectedLanguage = languages[which]
                languageSelector.text = selectedLanguage
                updateAppLanguage(selectedLanguage)
            }
            builder.show()
        }
//variables are created to handle the users email address and password settigns
        val user = Firebase.auth.currentUser
        var email = findViewById<EditText>(R.id.EmailField)
        val updateEmail = findViewById<Button>(R.id.ConfirmEmailChange)
        val updatePassword = findViewById<TextView>(R.id.UpdatePassword)
        val DeleteAcc = findViewById<TextView>(R.id.DeleteAccount)
        if (user != null) {
            email.setText(user.email.toString())
        }

        updatePassword.setOnClickListener {
            val user = Firebase.auth.currentUser
            user?.let()
            {
                for (profile in it.providerData) {
                    val email = profile.email

                    Firebase.auth.sendPasswordResetEmail(email.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("", "Email sent.")
                                Toast.makeText(
                                    this,
                                    "Email Sent To Reset Password",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            }
        }// update pass


        updateEmail.setOnClickListener {
            user!!.updateEmail(email.toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("", "User email address updated.")
                    Toast.makeText(this, "Email address Updated", Toast.LENGTH_LONG).show()
                }
            }
        }// update email

        DeleteAcc.setOnClickListener {
            val user = Firebase.auth.currentUser!!
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(
                            "",
                            "User account deleted."
                        )
                        Toast.makeText(this, "Account Deleted", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
        }// delete acc

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_settings -> {
                    true
                }
                R.id.navigation_hub -> {
                    // Open HubActivity
                    val intent = Intent(this, HubActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }// ?
    }// on create

    private fun getCurrentLanguage(): String {
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        return prefs.getString("selected_language", getDeviceDefaultLanguage()) ?: "English"
    }

    private fun getDeviceDefaultLanguage(): String {
        val locale = Locale.getDefault()
        return when (locale.language) {
            "en" -> "English"
            "zu" -> "Zulu"
            "af" -> "Afrikaans"
            else -> "English" // Default to English
        }
    }

    private fun updateAppLanguage(language: String){
        val locale = when (language){
            "English" -> Locale("en")
            "Zulu" -> Locale("zu")
            "Afrikaans" -> Locale("af")
            else -> Locale("en")
        }

        Locale.setDefault(locale)

        val prefs = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        prefs.edit().putString("selected_language", language).apply()

        setLocale(locale)
        recreate()
    }

    private fun setLocale(locale: Locale) {
        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        // Update configuration
        resources.updateConfiguration(config, resources.displayMetrics)

        // Optionally, set the configuration for the base context if using AppCompat
        applicationContext.createConfigurationContext(config)
    }

}