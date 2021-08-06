package com.example.desafiostant.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class MovieResponse(
    @SerializedName("results")
    val results: Response<Movie>,
)