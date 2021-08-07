package com.example.desafiostant.data.repository

import android.util.Log
import com.example.desafiostant.data.model.Genre
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.remote.RemoteClient

object MovieRepository {

    private val api = RemoteClient.createService
    private val movieList = arrayListOf<Movie>()
    private val genreList = arrayListOf<Genre>()

    suspend fun getAllMovies(): List<Movie> {
        val request = api.getPopularMovies()
        if (request.isSuccessful){
            request.body()?.let { result ->
                movieList.addAll(result.results)
            }
        }else{
            Log.d("***List", request.errorBody().toString())
        }
        Log.d("***List", movieList.toString())
        return movieList
    }

    suspend fun getGenres(): List<Genre> {
        val request = api.getGenres()
        if (request.isSuccessful){
            request.body()?.let { result ->
                genreList.addAll(result.genres)
            }
        }else{
            Log.d("***List", "request.errorBody().toString()")
        }
        Log.d("***List", genreList.toString())
        return genreList
    }
}