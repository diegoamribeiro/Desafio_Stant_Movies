package com.example.desafiostant.view.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.desafiostant.R
import com.example.desafiostant.data.database.MovieDatabase
import com.example.desafiostant.data.repository.MovieRepository
import com.example.desafiostant.data.repository.MovieViewModelProvideFactory
import com.example.desafiostant.data.viewmodel.HomeViewModel


class MoviesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val navController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean{
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}