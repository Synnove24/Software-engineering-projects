package com.example.myhellokittyquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myhellokittyquiz.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.example.android.hellokittyquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.example.android.myhellokittyquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    private var isCheater = false
    private val quizViewModel: QuizViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        //restore status after turning screen
        if (savedInstanceState != null) {
            isCheater = savedInstanceState.getBoolean(IS_CHEATER_KEY, false)
            if (isCheater) {
                val answerText = if (answerIsTrue) R.string.true_text else R.string.false_text
                binding.answerTextView.setText(answerText)
                setAnswerShownResult(true)  // Return result indicating answer is shown
            }
        }

        binding.showAnswerButton.setOnClickListener {
            val answerText = if (answerIsTrue) R.string.true_text else R.string.false_text
            binding.answerTextView.setText(answerText)
            quizViewModel.markQuestionCheated()
            isCheater = true
            setAnswerShownResult(true)
            finish()
        }
        //go back to quiz
        binding.root.setOnClickListener {
            finish()
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
            putExtra(IS_CHEATER_KEY, isCheater)
        }
        setResult(Activity.RESULT_OK, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_CHEATER_KEY, isCheater)
    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}