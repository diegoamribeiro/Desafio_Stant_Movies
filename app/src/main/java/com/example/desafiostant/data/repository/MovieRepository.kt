package com.example.desafiostant.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.model.MovieResponse
import com.example.desafiostant.data.remote.RemoteClient
import retrofit2.Response

object MovieRepository{

    private val api = RemoteClient.CREATE_SERVICE
    private lateinit var movieResponse: MovieResponse

    var name: String = ""

    suspend fun getAllMovies(page: Int): MovieResponse {
        val request = api.getPopularMovies(page = page)
        if (request.isSuccessful){
                request.body()?.let { result ->
                    movieResponse = result
            }
        }else{
            Log.d("***MovieRepository", request.errorBody().toString())
        }
        return movieResponse
    }



    suspend fun getGenres(id: Int): String {
        val request = api.getGenres()
        if (request.isSuccessful){
            request.body()?.let { result ->
                result.id = id
            }
        }else{
            Log.d("***MovieRepository", name)
        }
        Log.d("***MovieRepository", name)
        return name
    }
}