package com.example.desafiostant.view.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.desafiostant.R
import com.example.desafiostant.data.repository.MovieRepository
import com.example.desafiostant.data.viewmodel.HomeViewModel
import com.example.desafiostant.databinding.FragmentHomeBinding
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()
        CoroutineScope(Dispatchers.IO).launch {
            val value = MovieRepository.getGenres(28)
            Log.d("***getGenres", value)
        }

        return binding.root
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvMovies
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = listAdapter

        homeViewModel.getAllMovies.observe(viewLifecycleOwner,  { data->
            listAdapter.setData(data)
        })
    }


}