package com.example.desafiostant.view.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.desafiostant.R


class MoviesActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val homeContainerView = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = homeContainerView.navController
        setupActionBarWithNavController(homeContainerView.navController)
    }

    override fun onSupportNavigateUp(): Boolean{
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}