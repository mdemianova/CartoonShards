package com.example.cartoonshards.gameFragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cartoonshards.R
import com.example.cartoonshards.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private val gameTime = 60000L
    private val second = 1000L
    private lateinit var binding: FragmentGameBinding
    private var questionIndex = 0

    data class Question(val image: Int, val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            image = R.drawable.tom_jerry,
            answers = listOf("Tom & Jerry", "Finding Nemo", "Woody Woodpecker", "Lion King")
        ),
        Question(
            image = R.drawable.stitch,
            answers = listOf("Lilo & Stitch", "Jungle Book", "Bamby", "Duck Story")
        )
    )

//    init {
//        timer = object : CountDownTimer(gameTime, second) {
//            override fun onTick(millisUntilFinished: Long) {
//                currentTime = (millisUntilFinished / second).toString()
//            }
//
//            override fun onFinish() {
//                TODO("Not yet implemented")
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameBinding.inflate(layoutInflater)
        randomizeQuestions()
        binding.imageView.setImageResource(questions[questionIndex].image)
        setButtons()

        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
    }

    private fun setButtons() {
        val shuffledAnswers = questions[questionIndex].answers.toMutableList()
        shuffledAnswers.shuffle()

        with(binding) {
            answer1.text = shuffledAnswers[0]
            answer2.text = shuffledAnswers[1]
            answer3.text = shuffledAnswers[2]
            answer4.text = shuffledAnswers[3]
        }
    }
}