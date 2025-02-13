package com.example.myhellokittyquiz

import androidx.annotation.StringRes

//trying to make a data class, each object of the class
//is a question object, each question object
//will consist of a question identified by an id
//and an answer (boolean)
//data class Question (@StringRes val textResId: Int, val answer: Boolean, var answered: Boolean = false)
data class Question(@StringRes val textResId: Int, val answer: Boolean, var cheated: Boolean = false, var answered: Boolean = false)