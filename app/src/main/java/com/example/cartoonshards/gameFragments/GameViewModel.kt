package com.example.cartoonshards.gameFragments

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private lateinit var _currentQuestion: Question
    val currentQuestion: Question
        get() = _currentQuestion

    private var _answersArray = mutableListOf<Int>()
    val answersArray: List<Int>
        get() = _answersArray

    private val usedQuestions = mutableListOf<Question>()

    private var _score: Int = 0
    val score: Int
        get() = _score

    fun addScore() {
        _score += 100
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
        _currentQuestion = allQuestionsList.random()
        if (_currentQuestion in usedQuestions) {
            setupNewQuestion()
        }
        usedQuestions.add(_currentQuestion)
    }

    private fun setupOldQuestion() {
        val tempQuestion = allQuestionsList.random()
        if (tempQuestion == _currentQuestion) {
            setupOldQuestion()
        } else {
            _currentQuestion = tempQuestion
        }
    }

    private fun setupAnswersArray() {
        _answersArray.clear()
        _answersArray.add(_currentQuestion.answer)
        repeat(3) {
            addAnswer()
        }
        _answersArray.shuffle()
    }

    private fun addAnswer() {
        val tempAnswer = allAnswersList.random()
        if (tempAnswer in _answersArray) {
            addAnswer()
        } else {
            _answersArray.add(tempAnswer)
        }
    }

    private fun usedQuestionsCheck(): Boolean {
        return usedQuestions.containsAll(allQuestionsList)
    }
}