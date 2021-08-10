package com.example.desafiostant.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class MovieResponse(
    @SerializedName("results")
    var results: MutableList<Movie>,
    @SerializedName("page")
    var page: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
)