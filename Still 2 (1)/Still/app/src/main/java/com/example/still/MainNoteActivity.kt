package com.example.still

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainNoteActivity : AppCompatActivity() {

    // Firebase database reference
    private lateinit var database: DatabaseReference

    // Notification channel ID
    private val CHANNEL_ID = "note_saved_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_note)

        // Initialize Firebase database
        database = FirebaseDatabase.getInstance().reference
        database.child("notes").keepSynced(true)

        // Create notification channel
        createNotificationChannel()


        val noteTitle = findViewById<EditText>(R.id.noteTitle)
        val noteDescription = findViewById<EditText>(R.id.noteDescription)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val viewNotesButton = findViewById<Button>(R.id.viewNotesButton)


        val noteId = intent.getStringExtra("noteId")
        val title = intent.getStringExtra("noteTitle")
        val description = intent.getStringExtra("noteDescription")

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userUUID = currentUser?.uid

        // If noteId is not null, it's an edit operation
        if (!noteId.isNullOrEmpty()) {
            noteTitle.setText(title) // Set the title
            noteDescription.setText(description) // Set the description
        }

        // Save button click listener
        saveButton.setOnClickListener {
            val titleText = noteTitle.text.toString().trim()
            val descriptionText = noteDescription.text.toString().trim()

            // Validate input
            if (titleText.isEmpty()) {
                Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a note with the current date
            val currentDate = getCurrentDate()
            val note = Note(
                uuid = userUUID.toString(),
                id = noteId ?: database.push().key ?: "", // Use the existing note ID or create a new one
                title = titleText,
                description = descriptionText,
                date = currentDate
            )

            // Save the note to Firebase
            saveNoteToFirebase(note)
        }

        // View Notes button click listener
        viewNotesButton.setOnClickListener {
            val intent = Intent(this, NoteViewActivity::class.java)
            startActivity(intent)  // Navigate to the NoteViewActivity
        }
    }

    // Function to get the current date in "YYYY-MM-DD" format
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    // Function to save a note to Firebase Realtime Database
    private fun saveNoteToFirebase(note: Note) {
        database.child("notes").child(note.id).setValue(note)
            .addOnSuccessListener {
                Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show()

                // Send notification after successful save
                showNotification("Note Saved", "Your note was saved successfully.")

                // Clear input fields after saving
                findViewById<EditText>(R.id.noteTitle).text.clear()
                findViewById<EditText>(R.id.noteDescription).text.clear()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show()
            }
    }

    // Create notification channel for Android O and above
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Note Notifications"
            val descriptionText = "Notifications when notes are saved"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(title: String, message: String) {
        // Check if the POST_NOTIFICATIONS permission is granted (for Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Request the permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE_NOTIFICATION)
                return
            }
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.applogo)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(2, builder.build())
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_NOTIFICATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, proceed with showing the notification
                showNotification("Note Saved", "Your note was saved successfully.")
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_NOTIFICATION = 1
    }
}