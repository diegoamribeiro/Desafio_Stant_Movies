package com.example.desafiostant.data.repository

import com.example.desafiostant.data.database.MovieDatabase
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.remote.RetrofitClient

class MovieRepository(
    val movieDatabase: MovieDatabase
){
    suspend fun getNowPlaying(pageNumber: Int) =
        RetrofitClient.createService.getNowPlaying()

    //suspend fun upsert(movie: Movie) = movieDatabase.getMovieDao().upsert(movie)
}