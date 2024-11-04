package com.example.still

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MemoryActivity : BaseActivity() {
//This val contains the images for the meory game
    private val images = arrayOf(
        R.drawable.camel, R.drawable.coala, R.drawable.fox,
        R.drawable.lion, R.drawable.monkey, R.drawable.wolf,
        R.drawable.camel, R.drawable.coala, R.drawable.fox,
        R.drawable.lion, R.drawable.monkey, R.drawable.wolf
    )
//These variables are declared to handle the logic of the game
    private var firstSelected: ImageView? = null
    private var secondSelected: ImageView? = null
    private var firstImageId: Int = 0
    private var secondImageId: Int = 0
    private lateinit var gridLayout: GridLayout
    private lateinit var attemptsTextView: TextView
    private var attemptsLeft: Int = 10
    private var matchesFound: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        gridLayout = findViewById(R.id.gridLayout)
        attemptsTextView = findViewById(R.id.attemptsTextView)
        val attemptsLeft = 10 // Replace this with the actual attempts count
        attemptsTextView.text = getString(R.string.attempts_left, attemptsLeft)
        setupGame()
        updateAttemptsDisplay() // Initialize the attempts display
    }
//This method initializes the game
    private fun setupGame() {
        val shuffledImages = images.toMutableList().apply { shuffle() }

        for (imageId in shuffledImages) {
            val imageView = ImageView(this).apply {
                setImageResource(R.drawable.code) // Default background
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 155 // Height of each card
                    columnSpec = GridLayout.spec(
                        GridLayout.UNDEFINED,
                        1f
                    ) // Weight for equal distribution
                    setMargins(0, 8, 0, 8)
                }
                tag = imageId
                setOnClickListener { onCardClicked(this) }
            }
            gridLayout.addView(imageView)
        }
    }
//This method handles when the user clicks on an image
    private fun onCardClicked(view: View) {
        val imageView = view as ImageView
        val imageId = imageView.tag as Int

        if (firstSelected == null) {
            firstSelected = imageView
            firstImageId = imageId
            flipCard(imageView, imageId) // Show image with flip animation
        } else if (secondSelected == null && imageView != firstSelected) {
            secondSelected = imageView
            secondImageId = imageId
            flipCard(imageView, imageId) // Show image with flip animation
            checkForMatch() // Check for match after selecting the second card
        }
    }
//This method handles the animation of  flipping the card
    private fun flipCard(imageView: ImageView, imageId: Int) {
        val flipAnimation = ObjectAnimator.ofFloat(imageView, "rotationY", 0f, 90f)
        flipAnimation.duration = 250 // Duration of the first half of the flip
        flipAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                imageView.setImageResource(imageId) // Show image on the back side
                val backToFront = ObjectAnimator.ofFloat(imageView, "rotationY", 90f, 0f)
                backToFront.duration = 250 // Duration of the second half of the flip
                backToFront.start() // Start the second half of the flip
            }
        })
        flipAnimation.start() // Start the first half of the flip
    }
//This method checks if the user has found a match
    private fun checkForMatch() {
        if (firstImageId == secondImageId) {
            Toast.makeText(this, "Match!", Toast.LENGTH_SHORT).show()
            matchesFound++
            resetSelection()
            if (matchesFound == images.size / 2) {
                // Call the winning animation here
                winningAnimation()
            }
        } else {
            attemptsLeft-- // Decrement attempts only if not a match
            updateAttemptsDisplay() // Update the attempts display
            Toast.makeText(this, "Try Again!", Toast.LENGTH_SHORT).show()

            gridLayout.postDelayed({
                flipCardBack(firstSelected) // Hide first card
                flipCardBack(secondSelected) // Hide second card
                resetSelection()
                if (attemptsLeft <= 0) {
                    Toast.makeText(this, "Game Over!", Toast.LENGTH_LONG).show()
                    disableAllCards()
                }
            }, 1000)
        }
    }
//This method handles the number of attempts the user can make
    private fun updateAttemptsDisplay() {
        attemptsTextView.text = "$attemptsLeft" // Update attempts display

        // Change text color based on attempts left
        when (attemptsLeft) {
            in 7..10 -> attemptsTextView.setTextColor(Color.GREEN)
            in 4..6 -> attemptsTextView.setTextColor(Color.rgb(255, 165, 0)) // Orange for 4 to 6 attempts
            in 1..3 -> attemptsTextView.setTextColor(Color.RED)
            else -> attemptsTextView.setTextColor(Color.RED) // Red for 0 or less
        }
    }
//If the user wins, this method will handle the animation that will be shown to the user
    private fun winningAnimation() {
        for (i in 0 until gridLayout.childCount) {
            val imageView = gridLayout.getChildAt(i) as ImageView
            ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.5f).apply {
                duration = 500
                start()
            }
            ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f).apply {
                duration = 500
                start()
            }
            ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f).apply {
                duration = 500
                start()
            }
        }
        Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show()
    }
//This method handles when the card is flipped back to invisible
    private fun flipCardBack(imageView: ImageView?) {
        imageView?.let {
            val flipAnimation = ObjectAnimator.ofFloat(it, "rotationY", 0f, 90f)
            flipAnimation.duration = 250
            flipAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    it.setImageResource(R.drawable.code) // Reset to default background
                    val backToFront = ObjectAnimator.ofFloat(it, "rotationY", 90f, 0f)
                    backToFront.duration = 250
                    backToFront.start()
                }
            })
            flipAnimation.start()
        }
    }
//This method resets whatever the user has selected
    private fun resetSelection() {
        firstSelected = null
        secondSelected = null
        firstImageId = 0
        secondImageId = 0
    }
//All the options on the screen are not clickable
    private fun disableAllCards() {
        for (i in 0 until gridLayout.childCount) {
            val imageView = gridLayout.getChildAt(i) as ImageView
            imageView.isEnabled = false // Disable all cards
        }
    }
}
