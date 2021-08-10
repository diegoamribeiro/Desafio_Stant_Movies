package com.example.desafiostant.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafiostant.data.viewmodel.HomeViewModel


class MovieViewModelProvideFactory(
    val movieRepository: MovieRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieRepository) as T
    }
}