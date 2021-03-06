package com.example.desafiostant.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.desafiostant.data.model.Genre
import com.example.desafiostant.data.model.Movie
import java.security.AccessControlContext

@Database(entities = [Movie::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun getMovieDao(): MovieDao

    companion object{
        @Volatile
        private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) =
             Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_db"
            ).build()

    }
}