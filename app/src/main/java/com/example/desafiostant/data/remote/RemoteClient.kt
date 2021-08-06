package com.example.desafiostant.data.remote

import com.example.desafiostant.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteClient {

    private lateinit var retrofit: Retrofit

    val client = OkHttpClient()
    private fun getRetrofitInstance(): Retrofit {
        if(!::retrofit.isInitialized){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun <T>createService(serviceClass: Class<T>): T =  getRetrofitInstance().create(serviceClass)
}