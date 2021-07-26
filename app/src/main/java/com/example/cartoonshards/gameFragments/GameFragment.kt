package com.example.cartoonshards.gameFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.cartoonshards.R
import com.example.cartoonshards.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private var questionIndex = 0
    private lateinit var shards: List<View>

    data class Question(val image: Int, val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            image = R.drawable.tom_jerry,
            answers = listOf("Tom & Jerry", "Finding Nemo", "Woody Woodpecker", "Lion King")
        ),
        Question(
            image = R.drawable.stitch,
            answers = listOf("Lilo & Stitch", "Jungle Book", "Bamby and 1000 Rabbits", "Duck Story")
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameBinding.inflate(layoutInflater)

        shards = listOf(
            binding.shard1, binding.shard2, binding.shard3, binding.shard4,
            binding.shard5, binding.shard6, binding.shard7, binding.shard8,
            binding.shard9, binding.shard10, binding.shard11, binding.shard12,
            binding.shard12, binding.shard13, binding.shard14, binding.shard15,
            binding.shard16
        )

        randomizeQuestions()
        setScreen()
        setListener()
        setAnswerListener()

        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
    }

    private fun setScreen() {
        binding.imageView.setImageResource(questions[questionIndex].image)
        val shuffledAnswers = questions[questionIndex].answers.toMutableList()
        shuffledAnswers.shuffle()

        with(binding) {
            answer1.text = shuffledAnswers[0]
            answer2.text = shuffledAnswers[1]
            answer3.text = shuffledAnswers[2]
            answer4.text = shuffledAnswers[3]
        }
    }

    private fun setListener() {
        for (item in shards) {
            item.setOnClickListener { it.visibility = View.INVISIBLE }
        }
    }

    private fun setAnswerListener() {
        val answerButton: List<Button> = listOf(
            binding.answer1, binding.answer2, binding.answer3, binding.answer4
        )

        for (item in answerButton) {
            item.setOnClickListener {
                if (item.text == questions[questionIndex].answers[0]) {
                    questionIndex = (questionIndex + 1) % questions.size
                    refreshShards()
                    setScreen()
                } else {
                    Toast.makeText(this.context, "Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun refreshShards() {
        for (item in shards) {
            item.visibility = View.VISIBLE
        }
    }
}