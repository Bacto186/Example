package com.example.exampleapp.guesstheword.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.exampleapp.R
import com.example.exampleapp.databinding.FragmentScoreWordBinding

class ScoreWordFragment : Fragment() {

    private lateinit var viewModel: ScoreWordViewModel
    private lateinit var viewModelFactory: ScoreWordViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentScoreWordBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_score_word, container, false)

        viewModelFactory = ScoreWordViewModelFactory(ScoreWordFragmentArgs.fromBundle(arguments!!).score)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScoreWordViewModel::class.java)
        binding.scoreWordViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreWordFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        })
        return binding.root
    }

}