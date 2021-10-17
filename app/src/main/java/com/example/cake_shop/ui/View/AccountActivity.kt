package com.example.cake_shop.ui.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityAccountBinding

@Suppress("DEPRECATION")
class AccountActivity : AppCompatActivity() {

    private lateinit var accountActivityBinding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_account)

        accountActivityBinding.navAccount.setBackgroundColor(Color.parseColor("#E3D312"))

        val homeActivityIntent: Intent =
            Intent(this, HomeActivity::class.java)
        val aboutUsActivityIntent: Intent =
            Intent(this, AboutUsActivity::class.java)
        val infoActivityIntent: Intent =
            Intent(this, InfoActivity::class.java)
        val mainScreenActivity =
            Intent(this, MainActivity::class.java)

        accountActivityBinding.btnSignOut.setOnClickListener {
            startActivity(mainScreenActivity)
        }
        accountActivityBinding.navHome.setOnClickListener {
            setResult(0, homeActivityIntent)
            finish()
        }
        accountActivityBinding.navAboutUs.setOnClickListener {
            startActivityForResult(aboutUsActivityIntent, 1)
            setResult(0)
        }
        accountActivityBinding.navInfo.setOnClickListener {
            startActivityForResult(infoActivityIntent, 1)
            setResult(0)
        }
    }
}