package com.example.still

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : BaseActivity() {
//Various values are declared to handle the mood tracker popup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

//The instance of firebase and the database is stored in the variables


//Buttons and imageViews are made equal to variables
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val winddownButton = findViewById<ImageView>(R.id.windownbtn)
        val notepadbutton = findViewById<ImageView>(R.id.notepadbtn)
        val arcadeButton = findViewById<ImageView>(R.id.arcadebtn)
        val focuButton = findViewById<ImageView>(R.id.focusbtn)
        val meditateButton = findViewById<ImageView>(R.id.meditatebtn)
        val breatheButton = findViewById<ImageView>(R.id.breathebtn)
        val notepadButton = findViewById<ImageView>(R.id.notepadbtn)


        meditateButton.setOnClickListener {
            val intent = Intent(this, MeditateActivity::class.java)
            startActivity(intent)
        }
        notepadButton.setOnClickListener {
            val intent = Intent(this, MainNoteActivity::class.java)
            startActivity(intent)
        }
        breatheButton.setOnClickListener {
            val intent = Intent(this, BreatheActivity::class.java)
            startActivity(intent)
        }
        winddownButton.setOnClickListener {
            val intent = Intent(this, WindDownActivity::class.java)
            startActivity(intent)
        }
        focuButton.setOnClickListener {
            val intent = Intent(this, FocusActivity::class.java)
            startActivity(intent)
        }
//If the arcarde button is clicked then the mini games will open
        arcadeButton.setOnClickListener {
            val intent = Intent(this, ArcadeHubActivity::class.java)
            startActivity(intent)
        }
//This handles the bottom nav bar
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // We are already on the Home page, no need to do anything
                    true
                }
                R.id.navigation_hub -> {
                    // Open HubActivity
                    val intent = Intent(this, HubActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }


}
