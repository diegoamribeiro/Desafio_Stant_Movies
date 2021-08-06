package com.example.desafiostant.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<Movie>,
)