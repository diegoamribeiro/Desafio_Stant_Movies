package com.example.desafiostant.data.remote

import com.example.desafiostant.data.MovieApiService
import com.example.desafiostant.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val createService: MovieApiService by lazy {
            retrofit.create(MovieApiService::class.java)
        }
    }

}