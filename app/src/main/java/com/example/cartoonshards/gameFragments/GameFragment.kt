package com.example.cartoonshards.gameFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cartoonshards.R
import com.example.cartoonshards.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding
    private lateinit var shards: List<View>
    private lateinit var answersButtonList: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameBinding.inflate(layoutInflater)

        answersButtonList = listOf(
            binding.answer1, binding.answer2, binding.answer3, binding.answer4
        )

        shards = listOf(
            binding.shard1, binding.shard2, binding.shard3, binding.shard4,
            binding.shard5, binding.shard6, binding.shard7, binding.shard8,
            binding.shard9, binding.shard10, binding.shard11, binding.shard12,
            binding.shard12, binding.shard13, binding.shard14, binding.shard15,
            binding.shard16
        )

        viewModel.setupQuestionAndAnswers()

        setScreen()
        setShardListener()
        setAnswerListener()

        return binding.root
    }


    private fun setScreen() {
        binding.imageView.setImageResource(viewModel.currentQuestion.image)
        binding.scoreView.text = getString(R.string.your_score_text, viewModel.score)
        with(binding) {
            answer1.text = getString(viewModel.answersArray[0])
            answer2.text = getString(viewModel.answersArray[1])
            answer3.text = getString(viewModel.answersArray[2])
            answer4.text = getString(viewModel.answersArray[3])
        }
    }

    private fun setShardListener() {
        for (item in shards) {
            item.setOnClickListener { it.visibility = View.INVISIBLE }
        }
    }

    private fun setAnswerListener() {
        for (item in answersButtonList) {
            item.setOnClickListener {
                if (item.text == getString(viewModel.currentQuestion.answer)) {
                    viewModel.addScore()
                    viewModel.setupQuestionAndAnswers()
                    refreshShards()
                    setScreen()
                } else {
                    Toast.makeText(this.context, "Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun refreshShards() {
        shards.forEach { it.visibility = View.VISIBLE }
    }
}