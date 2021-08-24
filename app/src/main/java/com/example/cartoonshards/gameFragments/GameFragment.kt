package com.example.cartoonshards.gameFragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
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
                if (viewModel.isGameStarted) {
                    scoreAnimationFade()
                }
                binding.scoreView.text = getString(R.string.your_score_text, newScore)
            })

        viewModel.currentTime.observe(viewLifecycleOwner,
            { newTime ->
                binding.timeView.text =
                    getString(R.string.time_left, DateUtils.formatElapsedTime(newTime))
            })

        viewModel.eventGameFinish.observe(viewLifecycleOwner,
            { isFinished ->
                if (isFinished) {
                    val finalScore = viewModel.score.value ?: 0
                    val action =
                        GameFragmentDirections.actionGameFragmentToFinishFragment(finalScore)
                    findNavController().navigate(action)
                    viewModel.onGameFinishComplete()
                }
            })

        setShardListener()
        setAnswerListener()

        return binding.root
    }

    override fun onPause() {
        if (viewModel.isGameStarted) {
            viewModel.saveTimer()
        }
        super.onPause()
    }

    override fun onResume() {
        if (viewModel.isGameStarted) {
            viewModel.restartTimer()
        }
        super.onResume()
    }

    private fun setShardListener() {
        for (item in shards) {
            item.setOnClickListener {
                fadeShard(it)
                viewModel.reduceScore()
            }
        }
    }

    private fun setAnswerListener() {
        for (item in answersButtonList) {
            item.setOnClickListener {
                if (item.text == getString(viewModel.currentQuestion.value!!.answer)) {
                    //correctAnswerScale(it)
                    viewModel.addScore()
                    viewModel.addTime()
                    //showFullPicture()
                    viewModel.setupQuestionAndAnswers()
                    refreshShards()
                } else {
                    wrongAnswerShake(it)
                    viewModel.reduceTime()

                }
                timeAnimationFade()
            }
        }
    }

    private fun refreshShards() {
        shards.forEach {
            it.visibility = View.VISIBLE
        }
    }

    private fun wrongAnswerShake(view: View) {
        val animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0f, -6f, 6f)
        animator.duration = 100
        animator.repeatCount = 3
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    private fun fadeShard(view: View) {
        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f)
        animator.duration = 200
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.INVISIBLE
                view.alpha = 1f
                view.isEnabled = true
            }
        })
        animator.start()
    }

    private fun scoreAnimationFade() {
        binding.scoreAnimation.alpha = 0f
        val animator = ObjectAnimator.ofFloat(binding.scoreAnimation, View.ALPHA, 1f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        if (viewModel.scoreIsIncreasing) {
            binding.scoreAnimation.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.correct_answer
                )
            )
            binding.scoreAnimation.text = getString(R.string.score_delta, "+", viewModel.scoreDelta)

        } else {
            binding.scoreAnimation.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.wrong_answer
                )
            )
            binding.scoreAnimation.text = getString(R.string.score_delta, "-", viewModel.scoreDelta)

        }
        animator.start()
    }

    private fun timeAnimationFade() {
        binding.timeAnimation.alpha = 0f
        val animator = ObjectAnimator.ofFloat(binding.timeAnimation, View.ALPHA, 1f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        if (viewModel.isTimeIncreasing) {
            binding.timeAnimation.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.correct_answer
                )
            )
            binding.timeAnimation.text =
                getString(R.string.time_delta, "+", viewModel.incrementTimeInSec)
        } else {
            binding.timeAnimation.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.wrong_answer
                )
            )
            binding.timeAnimation.text =
                getString(R.string.time_delta, "-", viewModel.decrementTimeInSec)
        }
        animator.start()
    }
}