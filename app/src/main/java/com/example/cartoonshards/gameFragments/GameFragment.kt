package com.example.cartoonshards.gameFragments

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cartoonshards.R
import com.example.cartoonshards.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding
    private lateinit var shards: List<View>
    private lateinit var answersButtonList: List<Button>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

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

        viewModel.currentQuestion.observe(viewLifecycleOwner,
            { newQuestion ->
                binding.imageView.setImageResource(newQuestion.image)
            })

        viewModel.answersArray.observe(viewLifecycleOwner,
            { newAnswersArray ->
                binding.answer1.text = getString(newAnswersArray[0])
                binding.answer2.text = getString(newAnswersArray[1])
                binding.answer3.text = getString(newAnswersArray[2])
                binding.answer4.text = getString(newAnswersArray[3])
            })

        viewModel.score.observe(viewLifecycleOwner,
            { newScore ->
                binding.scoreView.text = getString(R.string.your_score_text, newScore)
            })

        viewModel.currentTime.observe(viewLifecycleOwner,
            { newTime ->
                binding.timeView.text = getString(R.string.time_left, DateUtils.formatElapsedTime(newTime))

            })

        viewModel.eventGameFinish.observe(viewLifecycleOwner,
            {isFinished ->
                if (isFinished) {
                    val finalScore = viewModel.score.value ?: 0
                    val action = GameFragmentDirections.actionGameFragmentToFinishFragment(finalScore)
                    findNavController().navigate(action)
                    viewModel.onGameFinishComplete()
                }
            })

        setShardListener()
        setAnswerListener()

        return binding.root
    }

    private fun setShardListener() {
        for (item in shards) {
            item.setOnClickListener {
                it.visibility = View.INVISIBLE
                viewModel.reduceScore()
            }
        }
    }

    private fun setAnswerListener() {
        for (item in answersButtonList) {
            item.setOnClickListener {
                if (item.text == getString(viewModel.currentQuestion.value!!.answer)) {
                    viewModel.addScore()
                    viewModel.addTime()
                    viewModel.setupQuestionAndAnswers()
                    refreshShards()
                } else {
                    viewModel.reduceTime()
                    Toast.makeText(this.context, "Wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun refreshShards() {
        shards.forEach {
            it.visibility = View.VISIBLE
        }
    }
}