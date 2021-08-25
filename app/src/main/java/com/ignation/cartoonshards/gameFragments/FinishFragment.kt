package com.ignation.cartoonshards.gameFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.ads.AdRequest
import com.ignation.cartoonshards.R
import com.ignation.cartoonshards.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding
    private val scoreKey = "score_key"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFinishBinding.inflate(layoutInflater)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        val finishFragmentArgs by navArgs<FinishFragmentArgs>()
        val sharedPref = requireActivity().getSharedPreferences(
            getString(R.string.shared_pref_name),
            Context.MODE_PRIVATE
        )

        val newHighScore =
            if (finishFragmentArgs.score > sharedPref.getInt(scoreKey, 0)) {
                finishFragmentArgs.score
            } else {
                sharedPref.getInt(scoreKey, 0)
            }
        sharedPref.edit().putInt(scoreKey, newHighScore).apply()

        binding.highScoreText.text = getString(R.string.high_score, sharedPref.getInt(scoreKey, 0))
        binding.finalScoreText.text = getString(R.string.your_score_text, finishFragmentArgs.score)
        binding.playAgainButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_finishFragment_to_gameFragment)
        }

        return binding.root
    }
}