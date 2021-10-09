package com.example.cake_shop.ui.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivitySplashScreenBinding
import com.example.cake_shop.ui.ViewModel.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivitySplashScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        binding.ivLoading.alpha = 0f
        binding.ivLoading.animate().setDuration(5000).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            startActivity(intent)
            finish()
        }

    }
}