package com.nureddinelmas.articles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.nureddinelmas.articles.R

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
/*		val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation) as NavHostFragment
		
		val navController = navHostFragment.navController
		setupActionBarWithNavController(navController)*/
	}
	
/*	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.navigation)
		
		return navController.navigateUp() || super.onSupportNavigateUp()
	}*/
}