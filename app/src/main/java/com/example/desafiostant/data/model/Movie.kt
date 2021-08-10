package com.example.desafiostant.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "poster_path")
    val poster_path: String,
    @ColumnInfo(name = "genre_ids")
    val genre_ids: ArrayList<Int>,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "overview")
    val overview: String,
): Parcelable