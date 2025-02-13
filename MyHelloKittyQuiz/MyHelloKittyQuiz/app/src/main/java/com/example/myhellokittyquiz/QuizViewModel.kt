package com.example.myhellokittyquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question1, false),
        Question(R.string.question2, true),
        Question(R.string.question3, false),
        Question(R.string.question4, false)
    )

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    private var answeredCount = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val isCheater: Boolean
        get() = questionBank[currentIndex].cheated

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = if (currentIndex == 0) {
            questionBank.size - 1
        } else {
            currentIndex - 1
        }
    }

    fun isQuestionAnswered(): Boolean {
        return questionBank[currentIndex].answered
    }

    fun markQuestionAnswered() {
        if (!questionBank[currentIndex].answered) {
            questionBank[currentIndex].answered = true
            answeredCount++
            Log.d(TAG, "Question ${currentIndex} answered. Updated answeredCount: $answeredCount")
        } else {
            Log.d(TAG, "Question ${currentIndex} was already answered.")
        }
    }

    fun markQuestionCheated() {
        questionBank[currentIndex].cheated = true // Mark the current question as cheated
    }

    fun getQuestionBankSize(): Int {
        return questionBank.size
    }

    fun getAnsweredCount(): Int {
        return answeredCount
    }

    fun setAnsweredCount(count: Int) {
        answeredCount = count
    }
}