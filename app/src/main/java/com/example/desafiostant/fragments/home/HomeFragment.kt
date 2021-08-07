package com.example.desafiostant.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.desafiostant.R
import com.example.desafiostant.data.repository.MovieRepository
import com.example.desafiostant.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvMovies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
            Log.d("###HomeFragment", "Click")
        }

        CoroutineScope(IO).launch {
            MovieRepository.getGenres()
        }

        //view.re

        return binding.root
    }


}