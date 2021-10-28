package com.example.cake_shop.ui.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {

    private lateinit var aboutUsBinding : ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutUsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_about_us)

        aboutUsBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }
    }
}