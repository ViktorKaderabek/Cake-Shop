package com.example.cake_shop.ui.animation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivitySplashScreenBinding
import com.example.cake_shop.ui.View.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        binding.ivLoading.alpha = 0f
        binding.ivLoading.animate().setDuration(5000).alpha(1f).withEndAction { // animace obrazku a kdyz skonci stanec se to co je v body

            val intent = Intent(this, MainActivity::class.java)

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)//typ animace
            startActivity(intent)
            finish()
        }
    }
}