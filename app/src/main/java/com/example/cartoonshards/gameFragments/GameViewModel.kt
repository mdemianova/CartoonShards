package com.example.cartoonshards.gameFragments

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        private const val ONE_SECOND = 1000L
        private const val DEFAULT_GAME_TIME = 200000L
        private const val INCREMENT_TIME = 3000L
        private const val DECREMENT_TIME = 2000L
        private const val GAME_OVER = 0L
        private const val CORRECT_GUESS_POINTS = 100
    }

    val incrementTimeInSec = INCREMENT_TIME / 1000
    val decrementTimeInSec = DECREMENT_TIME / 1000

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

    private var timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private var shardCounter = 0

    private var _scoreIsIncreasing = false
    val scoreIsIncreasing: Boolean
        get() = _scoreIsIncreasing

    private var _scoreDelta = 0
    val scoreDelta: Int
        get() = _scoreDelta

    private var _isGameStarted = false
    val isGameStarted: Boolean
    get() = _isGameStarted

    private var _isTimeIncreasing = false
    val isTimeIncreasing: Boolean
    get() = _isTimeIncreasing

    init {
        _answersArray.value = mutableListOf()
        _score.value = 0
        setupQuestionAndAnswers()
        timer = object : CountDownTimer(DEFAULT_GAME_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }
            override fun onFinish() {
                _currentTime.value = GAME_OVER
                _eventGameFinish.value = true
            }
        }

        timer.start()
    }

    fun addTime() {
        _isTimeIncreasing = true
        _isGameStarted = true
        val newTime = _currentTime.value!!.times(1000).plus(INCREMENT_TIME)
        timer.cancel()
        timer = object : CountDownTimer(newTime, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = GAME_OVER
                _eventGameFinish.value = true
            }
        }
        timer.start()
    }

    fun reduceTime() {
        _isTimeIncreasing = false
        _isGameStarted = true
        val newTime = _currentTime.value!!.times(1000).minus(DECREMENT_TIME)
        timer.cancel()
        timer = object : CountDownTimer(newTime, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = GAME_OVER
                _eventGameFinish.value = true
            }
        }
        timer.start()
    }


    fun addScore() {
        _scoreIsIncreasing = true
        _scoreDelta = CORRECT_GUESS_POINTS
        _score.value = _score.value!!.plus(CORRECT_GUESS_POINTS)
    }

    fun reduceScore() {
        _isGameStarted = true
        _scoreIsIncreasing = false
        val reduceAmount = when (shardCounter) {
            0 -> 0
            1 -> 10
            2 -> 15
            3 -> 20
            4 -> 25
            else -> 50
        }
        if (_score.value!!.minus(reduceAmount) < 0) {
            _scoreDelta = _score.value!!
            _score.value = 0
        } else {
            _scoreDelta = reduceAmount
            _score.value = _score.value!!.minus(reduceAmount)
        }
        shardCounter++
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
        shardCounter = 0
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

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}

//    // The String version of the current time
//    val currentTimeString = Transformations.map(currentTime) { time ->
//        DateUtils.formatElapsedTime(time)
//    }