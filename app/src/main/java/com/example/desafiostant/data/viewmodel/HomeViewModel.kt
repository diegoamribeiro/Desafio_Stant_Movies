package com.example.desafiostant.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.model.MovieResponse
import com.example.desafiostant.data.repository.MovieRepository
import com.example.desafiostant.utils.Constants.Companion.FIRST_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MovieRepository = MovieRepository

    private var _getAllMovies = MutableLiveData<MovieResponse>()
    val getAllMovies: LiveData<MovieResponse> get() = _getAllMovies

    private var _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> get() = _currentPage


    init {
        currentPage
        repository
        getAllMovies()
    }

    fun getAllMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            _getAllMovies.postValue(repository.getAllMovies(1))
        }
    }



}