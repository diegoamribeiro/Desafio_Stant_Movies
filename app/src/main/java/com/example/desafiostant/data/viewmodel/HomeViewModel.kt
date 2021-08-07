package com.example.desafiostant.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepository

    private var _getAllMovies = MutableLiveData<List<Movie>>()
    val getAllMovies: LiveData<List<Movie>> get() = _getAllMovies

    init {
        repository
        getAllMovies()

    }

    private fun getAllMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            _getAllMovies.postValue(repository.getAllMovies())
        }
    }


}