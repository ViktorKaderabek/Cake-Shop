package com.example.cake_shop.ui.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityInfoBinding

@Suppress("DEPRECATION")
class InfoActivity : AppCompatActivity() {

    private lateinit var inforActivityBinding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inforActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_info)

        inforActivityBinding.navInfo.setBackgroundColor(Color.parseColor("#E3D312"))

        val homeActivityIntent: Intent =
            Intent(this, HomeActivity::class.java)
        val accountActivityIntent: Intent =
            Intent(this, AccountActivity::class.java)
        val aboutUsActivityIntent: Intent =
            Intent(this, AboutUsActivity::class.java)

        inforActivityBinding.navHome.setOnClickListener {
            setResult(0,homeActivityIntent)
            finish()
        }

        inforActivityBinding.navAboutUs.setOnClickListener {
            startActivityForResult(aboutUsActivityIntent, 1)
            setResult(0)
        }

        inforActivityBinding.navAccount.setOnClickListener {
            startActivityForResult(accountActivityIntent, 1)
            setResult(0)
        }
    }
}