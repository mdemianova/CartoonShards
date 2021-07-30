package com.example.cartoonshards.gameFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private val _answersArray = MutableLiveData<MutableList<Int>>()
    val answersArray: LiveData<MutableList<Int>>
        get() = _answersArray

    private val usedQuestions = mutableListOf<Question>()

    private val tempAnswersArray = mutableListOf<Int>()

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    fun initializeData() {
        _answersArray.value = mutableListOf()
        _score.value = 0
        setupQuestionAndAnswers()
    }

    fun addScore() {
        _score.value = _score.value!!.plus(100)
    }

    fun setupQuestionAndAnswers() {
        setupCurrentQuestion()
        setupAnswersArray()
    }

    private fun setupCurrentQuestion() {
        if (usedQuestionsCheck()) {
            setupOldQuestion()
        } else {
            setupNewQuestion()
        }
    }

    private fun setupNewQuestion() {
        _currentQuestion.value = allQuestionsList.random()
        if (_currentQuestion.value in usedQuestions) {
            setupNewQuestion()
        }
        usedQuestions.add(_currentQuestion.value!!)
    }

    private fun setupOldQuestion() {
        val tempQuestion = allQuestionsList.random()
        if (tempQuestion == _currentQuestion.value) {
            setupOldQuestion()
        } else {
            _currentQuestion.value = tempQuestion
        }
    }

    private fun setupAnswersArray() {
        tempAnswersArray.clear()
        tempAnswersArray.add(_currentQuestion.value!!.answer)
        repeat(3) {
            addAnswer()
        }
        tempAnswersArray.shuffle()
        _answersArray.value = tempAnswersArray
    }

    private fun addAnswer() {
        val tempAnswer = allAnswersList.random()
        if (tempAnswer in tempAnswersArray) {
            addAnswer()
        } else {
            tempAnswersArray.add(tempAnswer)
        }
    }

    private fun usedQuestionsCheck(): Boolean {
        return usedQuestions.containsAll(allQuestionsList)
    }
}