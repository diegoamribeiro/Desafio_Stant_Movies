package com.example.desafiostant.data.model

import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("overview")
    val overview: String,


)