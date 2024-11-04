package com.example.still

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

// In ProgressActivity.kt

class PowerUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_power_up)
//tHE LIGHTBULB IS INSTANTIATED BY A VAL VARIABLE
        val lightBulb = findViewById<ImageView>(R.id.lightBulb)
        //the shared preferences of the light setting is retreived
        val sharedPreferences = getSharedPreferences("LightSettings", MODE_PRIVATE)

        val lightState = sharedPreferences.getInt("light_state", 0)
//the light brgihtnesss is set based on how many songs the user listens to and will light up accordingly
        when {
            lightState in 0..0 -> lightBulb.setImageResource(R.drawable.light_off)   // Light off
            lightState in 1..5 -> lightBulb.setImageResource(R.drawable.light_verydim)   // Dim light
            lightState in 6..10 -> lightBulb.setImageResource(R.drawable.light_dim) // Bright light
            lightState in 11..15 -> lightBulb.setImageResource(R.drawable.light_bright)   // Dim light
            lightState >=16 -> lightBulb.setImageResource(R.drawable.light_verybright) // Bright light

        }
//The state of the lightbulb is set to a variable
        val lightStateText = findViewById<TextView>(R.id.lightStateText)
//The following texts will be shown baed on how many songs the user has lsitened to
        lightStateText.text = when {
            lightState in 0..0 -> "You haven't listened to any songs yet"
            lightState in 1..5 -> "You started listening, keep going"
            lightState in 6..10 -> "You're a good listener, keep going"
            lightState in 11..15 -> "You're a pro listener, keep going"
            lightState >=16 -> "Wow, You've listened to alot. Well Done"
            else -> "Light off"
        }

    }
}
