package com.example.desafiostant.view.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.desafiostant.R
import androidx.appcompat.widget.SearchView
import com.example.desafiostant.data.database.MovieDatabase
import com.example.desafiostant.data.repository.MovieRepository
import com.example.desafiostant.data.repository.MovieViewModelProvideFactory
import com.example.desafiostant.view.fragments.viewmodel.HomeViewModel
import com.example.desafiostant.databinding.FragmentHomeBinding
import com.example.desafiostant.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.desafiostant.utils.Resource
import com.example.desafiostant.view.fragments.home.adapters.HomeAdapter

class HomeFragment : Fragment(), SearchView.OnQueryTextListener{

    private val listAdapter: HomeAdapter by lazy { HomeAdapter() }
    lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieRepository = MovieRepository(MovieDatabase(requireContext()))
        val viewModelProviderFactory = MovieViewModelProvideFactory(movieRepository)
        homeViewModel = ViewModelProvider(requireActivity(), viewModelProviderFactory).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeViewModel()
        setHasOptionsMenu(true)

        return binding.root
    }


    private fun observeViewModel(){
        homeViewModel.nowPlaying.observe(viewLifecycleOwner,  { response->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let { nowPlaying->
                        listAdapter.setData(nowPlaying.results.toList())
                        val totalPages = nowPlaying.totalPages / 2
                        isLastPage = homeViewModel.currentPage == totalPages
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message->
                        Log.d("homeViewModel", message)
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }

    val scrollListener = object : RecyclerView.OnScrollListener(){

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate){
                homeViewModel.getNowPlaying()
                isScrolling = false
            }else{
                binding.rvMovies.setPadding(0,0,0,0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private fun setupRecyclerView(){
        recyclerView = binding.rvMovies
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = listAdapter
        recyclerView.apply {
            addOnScrollListener(this@HomeFragment.scrollListener)
        }
    }


    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    private fun searChThroughDatabase(query: String) {
        val searchQuery = "%$query%"
        homeViewModel.searchDatabase(searchQuery).observe(this, Observer {list->
            list?.let {
                listAdapter.setData(it)
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searChThroughDatabase(query)
        }

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searChThroughDatabase(query)
        }
        return true
    }

}