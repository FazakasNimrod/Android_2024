package com.tasty.recipesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val TAG = "TAGSplashActivity"
    private lateinit var binding: ActivitySplashBinding

    private val SPLASH_TIME_OUT: Long = 2000


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.button.setOnClickListener {
//            val message = binding.editText.text.toString()
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("message", message)
//            startActivity(intent)
//        }
//
//        Log.d(TAG, "onCreate: SplashActivity created.")
//        Toast.makeText(this, "Elindult", Toast.LENGTH_LONG).show()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImageView = findViewById<ImageView>(R.id.logoImageView)
        val appNameTextView = findViewById<TextView>(R.id.appNameTextView)
        val taglineTextView = findViewById<TextView>(R.id.taglineTextView)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logoImageView.startAnimation(fadeIn)
        appNameTextView.startAnimation(fadeIn)
        taglineTextView.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: SplashActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: SplashActivity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: SplashActivity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: SplashActivity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: SplashActivity destroyed.")
    }
}
