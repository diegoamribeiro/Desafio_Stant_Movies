package com.example.desafiostant.data

import com.example.desafiostant.data.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET("/movie/popular")
    suspend fun getPopularMovies(): Call<List<Movie>>



}