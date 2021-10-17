package com.example.cake_shop.ui.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityAboutUsBinding

@Suppress("DEPRECATION")
class AboutUsActivity : AppCompatActivity() {

    private lateinit var aboutUsAcitivytBinding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        aboutUsAcitivytBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_about_us)

        aboutUsAcitivytBinding.navAboutUs.setBackgroundColor(Color.parseColor("#E3D312"))

        val homeActivityIntent: Intent =
            Intent(this, HomeActivity::class.java)
        val accountActivityIntent: Intent =
            Intent(this, AccountActivity::class.java)
        val infoActivityIntent: Intent =
            Intent(this, InfoActivity::class.java)

        aboutUsAcitivytBinding.navHome.setOnClickListener {
            setResult(0, homeActivityIntent)
            finish()
        }

        aboutUsAcitivytBinding.navAccount.setOnClickListener {
            startActivityForResult(accountActivityIntent, 1)
            setResult(0)
        }

        aboutUsAcitivytBinding.navInfo.setOnClickListener {
            startActivityForResult(infoActivityIntent, 1)
            setResult(0)
        }
    }
}