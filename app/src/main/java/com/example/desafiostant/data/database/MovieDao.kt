package com.example.desafiostant.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.desafiostant.data.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie): Long

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Movie>>

}