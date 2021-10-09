package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val loginIntent: Intent =
            Intent(this, LogginActivity::class.java)

        val signUpIntent: Intent =
            Intent(this, SignUpActivity::class.java)

        binding.btnSignup.setOnClickListener {

            startActivityForResult(signUpIntent, 1)
        }

        binding.btnLogin.setOnClickListener {

            startActivityForResult(loginIntent, 1)

        }
    }
}