package com.example.desafiostant.data.repository

import androidx.lifecycle.LiveData
import com.example.desafiostant.data.database.MovieDatabase
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.remote.RetrofitClient

class MovieRepository(
    val movieDatabase: MovieDatabase
){
    suspend fun getNowPlaying(pageNumber: Int) =
        RetrofitClient.createService.getNowPlaying()

    fun searchDatabase(searchQuery: String): LiveData<List<Movie>>{
        return movieDatabase.getMovieDao().searchDatabase(searchQuery)
    }
}