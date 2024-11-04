package com.example.still

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.still.databinding.ActivityQuizWhizBinding
import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class QuizWhizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizWhizBinding

    //////////////////////////////////////////////////
    /////////////// MOOD TRACKER CODE
    private var selectedMood: String? = null


    /////////////////////////////////////////////////////



    /////////////////////////////////////////////////
    ///////////QUIZ GAME CODE

    private val questions = arrayOf("How many hearts does an octopus have?" ,//1
        "Scurvy is caused by a lack of this vitamin?",//2
        "Where did the Olympic Games originate?",//3
        "Who was the Ancient Greek God of the Sun?",//4
        "How many minutes are in a full week?",//5
        "How many faces does a Dodecahedron have?",//6
        "What is the 4th letter of the Greek alphabet?",//7
        "What is a word, phrase, number, or other sequence of characters that reads the same backward as forward?",//8
        "In which country would you find Mount Kilimanjaro?",//9
        "Halloween originated as an ancient Irish festival.",//10
        "Which is the only continent with land in all four hemispheres?",//11
        "Which river flows through the Grand Canyon?",//12
        "On which continent would you find the world’s largest desert?",//13
        "How many colors are used in the South African flag?"//14
    )

    private val options = arrayOf(arrayOf("3","1","7"), //1
        arrayOf("Vitamin D","Vitamin C","Vitamin A"),//2
        arrayOf("Italy","USA","Greece"),//3
        arrayOf("Apollo", "Zeus", "Aries"),//4
        arrayOf("10,080", "14,201", "7,407"),//5
        arrayOf("10", "12", "14"),//6
        arrayOf("Beta", "Omega", "Delta"),//7
        arrayOf("Palindrome", "Cypher", "Cryptogram"),//8
        arrayOf("Kenya", "Tanzania", "China"),//9
        arrayOf("True", "False", "Unknown"),//10
        arrayOf("Asia", "South America", "Africa"),//11
        arrayOf("Amazon River", "Colorado River", "Nile River"),//12
        arrayOf("Antarctica", "Australia", "Africa"),//13
        arrayOf("7", "6", "10")
    )

    private val correctAnswers = arrayOf(0,1,2,0,0,1,2,0,1,0,2,1,0,1)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizWhizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ///// MOOD TRACKER //////
        // showMoodTrackerDialog()
        ///////////////

        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }

        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }

        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }

        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
    }

    private fun correctButtonColours(buttonIndex: Int) {
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColours(buttonIndex: Int) {
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColours() {
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        binding.scoreText.text = "Your score: $score out of ${questions.size}"
        // Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun disableButtons() {
        binding.option1Button.isEnabled = false
        binding.option2Button.isEnabled = false
        binding.option3Button.isEnabled = false
    }

    private fun enableButtons() {
        binding.option1Button.isEnabled = true
        binding.option2Button.isEnabled = true
        binding.option3Button.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColours()
        enableButtons()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColours(selectedAnswerIndex)
        } else {
            wrongButtonColours(selectedAnswerIndex)
            correctButtonColours(correctAnswerIndex)
        }
        disableButtons()
        if (currentQuestionIndex < questions.size -1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()}, 1000)
        } else{
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.scoreText.text =""
        binding.restartButton.isEnabled = false
    }


}