package com.example.desafiostant.data

import com.example.desafiostant.data.model.Genre
import com.example.desafiostant.data.model.GenreResponse
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.data.model.MovieResponse
import com.example.desafiostant.data.remote.RemoteClient
import com.example.desafiostant.utils.Constants.Companion.API_KEY
import com.example.desafiostant.utils.Constants.Companion.LANG_PTBR
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") lang: String = LANG_PTBR
    ): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String = API_KEY): Response<Genre>

}