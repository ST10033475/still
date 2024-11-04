package com.example.still

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)


        val title = intent.getStringExtra("noteTitle") ?: "No Title"
        val description = intent.getStringExtra("noteDescription") ?: "No Description"
        val date = intent.getStringExtra("noteDate") ?: "No Date"

        // Set the retrieved data to TextViews
        findViewById<TextView>(R.id.noteTitleTextView).text = title
        findViewById<TextView>(R.id.noteDescriptionTextView).text = description
        findViewById<TextView>(R.id.noteDateTextView).text = date

        // Back button functionality
        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()  // Finish the activity and go back to the previous one
        }
    }
}