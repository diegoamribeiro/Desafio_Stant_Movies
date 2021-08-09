package com.example.desafiostant.view.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.desafiostant.R
import com.example.desafiostant.data.repository.MovieRepository
import com.example.desafiostant.data.viewmodel.HomeViewModel
import com.example.desafiostant.databinding.FragmentHomeBinding
import com.example.desafiostant.utils.Constants.Companion.FIRST_PAGE
import com.example.desafiostant.view.fragments.home.adapters.HomeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val listAdapter: HomeAdapter by lazy { HomeAdapter() }
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var currentPage = 1
    private var totalPages = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        getAllMovies()
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvMovies
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = listAdapter
    }

    private fun getAllMovies(){
        homeViewModel.getAllMovies()
        homeViewModel.getAllMovies.observe(viewLifecycleOwner, Observer {
            listAdapter.setData(it.results)
        })

    }
}