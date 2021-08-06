package com.example.desafiostant.data.repository

import android.util.Log
import com.example.desafiostant.data.MovieApi
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.model.MovieResponse
import com.example.desafiostant.data.remote.RemoteClient

class MovieRepository(private val movieApi: MovieApi) {

    private val movieList = arrayListOf<Movie>()

    suspend fun getPopularMovies(): List<Movie> {
        val request = RemoteClient.createService(MovieApi::class.java).getPopularMovies().execute()
        if (request.isSuccessful){
            request.body()?.let { result ->
                movieList.addAll(result)
            }
        }else{
            Log.d("***List", request.errorBody().toString())
        }
        Log.d("***List", movieList.toString())
        return movieList
    }
}