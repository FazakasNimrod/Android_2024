package com.tasty.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.tasty.recipesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("message") ?: "No message received"
        binding.textView.text = message

        Log.d(TAG, "onCreate: MainActivity created.")

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            val navController = findNavController(R.id.nav_host_fragment)
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true // Return true to indicate that the item selection was handled
                }
                R.id.recipesFragment -> {
                    navController.navigate(R.id.recipesFragment)
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                else -> false // Return false for unhandled selections
            }
        }

        binding.bottomNavigation.selectedItemId = R.id.homeFragment
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