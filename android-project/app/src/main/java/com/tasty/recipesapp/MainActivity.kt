package com.tasty.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.tasty.recipesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: MainActivity created.")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    navigateToFragment(HomeFragment())
                    true // Return true to indicate that the item selection was handled
                }
                R.id.recipesFragment -> {
                    navigateToFragment(RecipesFragment())
                    true
                }
                R.id.profileFragment -> {
                    navigateToFragment(ProfileFragment())
                    true
                }
                else -> false // Return false for unhandled selections
            }
        }

        binding.bottomNavigation.selectedItemId = R.id.homeFragment

    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: MainActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: MainActivity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: MainActivity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: MainActivity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MainActivity destroyed.")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                return true
            }
            R.id.recipesFragment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.recipesFragment)
                return true
            }
            R.id.profileFragment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}