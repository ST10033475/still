package com.example.still

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ArcadeHubActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcade_hub)

        val memButton = findViewById<Button>(R.id.memorygame)
        val quizButton = findViewById<Button>(R.id.quizwhizgame)


        memButton.setOnClickListener {
            val intent = Intent(this, MemoryActivity::class.java)
            startActivity(intent)
        }
        quizButton.setOnClickListener {
            val intent = Intent(this, QuizWhizActivity::class.java)
            startActivity(intent)
        }
    }
}