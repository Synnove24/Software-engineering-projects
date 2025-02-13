package com.example.myhellokittyquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myhellokittyquiz.databinding.ActivityMainBinding

//add some information that records the progress on my log
private const val TAG = "Main Activity Recording"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton:Button
    private lateinit var falseButton:Button
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private var score = 0
    private var number_answered = 0

    private val cheatLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val isCheater = result.data?.getBooleanExtra(IS_CHEATER_KEY, false) ?: false
            if (isCheater) {
                quizViewModel.markQuestionCheated()  // Mark the current question as cheated
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate(Bundle?) called")
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.TrueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            binding.TrueButton.isEnabled = false
            binding.FalseButton.isEnabled = false
            quizViewModel.markQuestionAnswered()
        }
        binding.FalseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            binding.TrueButton.isEnabled = false
            binding.FalseButton.isEnabled = false
            quizViewModel.markQuestionAnswered()
        }

        binding.NextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.PreviousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        binding.cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            cheatLauncher.launch(intent)  // Launch CheatActivity using the result launcher
        }

        updateQuestion()
    }


    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.textQuestion.setText(questionTextResId)
        if (quizViewModel.isQuestionAnswered()) {
            binding.TrueButton.isEnabled = false
            binding.FalseButton.isEnabled = false
        } else {
            binding.TrueButton.isEnabled = true
            binding.FalseButton.isEnabled = true
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() is called")
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> {
                score += 1
                R.string.judgment_toast
            }
            userAnswer == correctAnswer -> {
                score += 1
                R.string.correct_toast
            }
            else -> R.string.incorrect_toast
        }

        number_answered = number_answered + 1
        Snackbar.make(findViewById(android.R.id.content), messageResId, Snackbar.LENGTH_SHORT)
            .show()

        quizViewModel.markQuestionAnswered()


        Log.d(TAG, "Answered Count: ${quizViewModel.getAnsweredCount()}, Total Questions: ${quizViewModel.getQuestionBankSize()}")

        if (quizViewModel.getAnsweredCount() == quizViewModel.getQuestionBankSize()) {
            val percentage = (score * 100) / quizViewModel.getQuestionBankSize()
            Toast.makeText(this, "You scored $percentage%!", Toast.LENGTH_LONG).show()
        }
    }
}