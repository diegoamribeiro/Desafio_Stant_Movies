package com.example.desafiostant.data.repository

import android.util.Log
import com.example.desafiostant.data.model.Genre
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.remote.RemoteClient

object MovieRepository {

    private val api = RemoteClient.createService
    private val movieList = arrayListOf<Movie>()

    var name: String = ""

    suspend fun getAllMovies(): List<Movie> {
        val request = api.getPopularMovies()
        if (request.isSuccessful){
            request.body()?.let { result ->
                movieList.addAll(result.results)
            }
        }else{
            Log.d("***MovieRepository", request.errorBody().toString())
        }
        return movieList
    }

    suspend fun getGenres(id: Int): String {
        val request = api.getGenres()
        if (request.isSuccessful){
            request.body()?.let { result ->
                result.id = id
                result.name.map {  }
            }
        }else{
            Log.d("***MovieRepository", name)
        }
        Log.d("***MovieRepository", name)
        return name
    }
}