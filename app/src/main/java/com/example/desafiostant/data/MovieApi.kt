package com.example.desafiostant.data

import com.example.desafiostant.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET("/movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

}