package com.example.still

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Locale

class HubActivity : BaseActivity() {

    //Various values are declared to handle the mood tracker popup
    private val CHANNEL_ID = "mood_tracker_channel"
    private val sharedPrefFile = "com.example.still.preferences"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    //The database and firebase variables are declared
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Set up BottomNavigationView and set the current item as "Hub"
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Set up navigation for ImageButtons
        val vibeCheckButton = findViewById<ImageButton>(R.id.VibeCheckBtn)
        val serenaAIButton = findViewById<ImageButton>(R.id.SerenaAIBtn)
        val powerUpButton = findViewById<ImageButton>(R.id.PowerUpBtn)


//This redirects to the AI
        serenaAIButton.setOnClickListener {
            val intent = Intent(this, SerenaActivity::class.java)
            startActivity(intent)
        }

        powerUpButton.setOnClickListener {
        val intent = Intent(this, PowerUp::class.java)
            startActivity(intent)
        }

        vibeCheckButton.setOnClickListener {
            checkNotificationPermission()
            createNotificationChannel()
            showMoodTrackerDialog()
        }


        // Highlight the Hub button as selected
        bottomNavigationView.selectedItemId = R.id.navigation_hub

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_hub -> {
                    // Stay on the current page since this is HubActivity
                    true
                }
                //Redirect to the home page
                R.id.navigation_home -> {
                    val intent = Intent(this, HomePage::class.java)
                    startActivity(intent)
                    true
                }
                //Redirect to the settings in page
                R.id.navigation_settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


        // Load GIF banner for Hub
        val bannerImageView = findViewById<ImageView>(R.id.mindHubBanner)
        Glide.with(this)
            .asGif()
            .load(R.drawable.mindhubbanner)
            .into(bannerImageView)
    }

   //This method enables the app to send the user a notification on their phone
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Mood Tracker Notifications"
            val descriptionText = "Notifications for mood tracking"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    //This method shows the mood tracker dialog to the user and the user's input is sent to the checkAndShowNotification method
    private fun showMoodTrackerDialog() {
        val dialogView = layoutInflater.inflate(R.layout.mood_tracker, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        val emotionEntry: EditText = dialogView.findViewById(R.id.emotionEntry)
        val btnSubmitMood: Button = dialogView.findViewById(R.id.btnSubmitMood)

        btnSubmitMood.setOnClickListener {
            val userInput = emotionEntry.text.toString().trim().lowercase()
            val message = getEmotionResponse(userInput)
            checkAndShowNotification(message)
            dialog.dismiss()
        }

        dialog.show()
    }
    //This method checks the version OS of the phone and requests permissions based on that
    private fun checkAndShowNotification(message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                showNotification(message)
            } else {

                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE)
            }
        } else {

            showNotification(message)
        }
    }
    //This method sends the notification
    private fun showNotification(message: String) {
        // Check for permission again before showing the notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.applogo)
                    .setContentTitle("Mood Tracker Response")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                val notificationManager = NotificationManagerCompat.from(this)
                notificationManager.notify(1, builder.build())

            } else {
                showNotification("Cannot display notification: Permission denied.")
            }
        } else {
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.applogo)
                .setContentTitle("We Understand You 💡💙")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(1, builder.build())
        }
    }


    fun dismissDialog(view: View) {
        val dialog = view.parent?.parent as? AlertDialog // Reference the AlertDialog
        dialog?.dismiss() // Dismiss the dialog
    }

    //This method handles what the notification will say to the user based on their mood that they have input into the mood tracker
    private fun getEmotionResponse(input: String): String {
        val negativeEmotions = listOf(
            "sad", "depressed", "moody", "angry", "annoyed", "irritated", "disgusted", "suicidal"
        )
        val positiveEmotions = listOf("happy", "joy", "joyful", "elated", "excited", "grateful", "content", "amused")

        return when {
            negativeEmotions.any { input.contains(it) } -> {
                "Speak to Serena-ai, your emotional companion."
            }
            positiveEmotions.any { input.contains(it) } -> {
                "I'm glad you feel that way! Enjoy the app."
            }
            else -> {
                "Thanks for sharing your feelings!"
            }
        }
    }
    //This method checks if the app has permission to send notifications
    private fun checkNotificationPermission() {
        val sharedPref = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val isPermissionRequested = sharedPref.getBoolean("isPermissionRequested", false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED && !isPermissionRequested) {
                // Request permission if it hasn't been requested before
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE)

                // Save in SharedPreferences that the permission was requested
                with(sharedPref.edit()) {
                    putBoolean("isPermissionRequested", true)
                    apply()
                }
            } else if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                // Permission already granted, no need to show notification every time
                Log.d("Permission", "Notification permission already granted.")
            }
        } else {
            // No need to request permissions for API levels below 33
            Log.d("Permission", "No need to request POST_NOTIFICATION permission on this version.")
        }
    }


    // Handle the permission request result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showNotification("Permission granted, you can now receive notifications.")
                } else {
                    showNotification("Permission denied, you cannot receive notifications.")
                }
            }
        }
    }

    // Define a constant for the request code
    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1
    }}
