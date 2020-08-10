package com.example.exampleapp.guesstheword.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.exampleapp.R
import com.example.exampleapp.databinding.FragmentTitleBinding
import com.example.exampleapp.databinding.FragmentTitleWordBinding


class TitleWordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentTitleWordBinding>(inflater,R.layout.fragment_title_word,container, false)

        binding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleWordFragmentDirections.actionTitleToGame())
        }

        return binding.root
    }

}