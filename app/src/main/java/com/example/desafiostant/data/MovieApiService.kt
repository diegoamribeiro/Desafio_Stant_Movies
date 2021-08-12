package com.example.desafiostant.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafiostant.data.model.Genre
import com.example.desafiostant.data.model.MovieResponse
import com.example.desafiostant.utils.Constants.Companion.API_KEY
import com.example.desafiostant.utils.Constants.Companion.LANG_PTBR
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") lang: String = LANG_PTBR,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String = API_KEY,
                          @Query("language") lang: String = LANG_PTBR): Response<Genre>

}