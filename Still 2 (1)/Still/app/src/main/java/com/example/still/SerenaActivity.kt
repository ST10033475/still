package com.example.still

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SerenaActivity : BaseActivity() {
//Variables are declared to handle the conversation between Serena-AI and the user
    private lateinit var promptInput: EditText
    private lateinit var generateButton: ImageButton
    private lateinit var responseText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serena)

        promptInput = findViewById(R.id.promptInput)
        generateButton = findViewById(R.id.generateButton)
        responseText = findViewById(R.id.responseText)
//This is Serena-AI's first message to the user
        responseText.text = "Hello there! My name is Serena an emotional AI. I'm a confidential companion ready to hear your thoughts and feelings. No matter what you're going through, know that you're not alone ❤️"

        generateButton.setOnClickListener {
            val prompt = promptInput.text.toString().trim()
            if (prompt.isNotEmpty()) {
                // Disable typing while generating content
                generateButton.isEnabled = false
                promptInput.isFocusable = false
                promptInput.isFocusableInTouchMode = false

                generateContent(prompt)
            } else {
                responseText.text = "Please enter a prompt!"
            }
        }
    }
//This method handles the api call to Gemini
    private fun generateContent(prompt: String) {
        responseText.text = "Brainstorming \uD83D\uDCA1\uD83E\uDDE0 ..."

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-pro",
                    apiKey = "AIzaSyDbAtrWoL5o2B72BGvNZRnBWi4DnFO9PFw"
                )

                val response = generativeModel.generateContent(prompt)

                withContext(Dispatchers.Main) {
                    // Update UI with generated response
                    responseText.text = response.text

                    // Clear prompt input field
                    promptInput.text.clear()

                    // Re-enable button and input (allow typing again)
                    generateButton.isEnabled = true
                    promptInput.isFocusable = true
                    promptInput.isFocusableInTouchMode = true
                }
            } catch (e: Exception) {
                Log.e("SerenaActivity", "Error generating content", e)
                withContext(Dispatchers.Main) {
                    responseText.text = "Failed to generate content: ${e.message}"

                    // Re-enable button and input on failure
                    generateButton.isEnabled = true
                    promptInput.isFocusable = true
                    promptInput.isFocusableInTouchMode = true
                }
            }
        }
    }
}
