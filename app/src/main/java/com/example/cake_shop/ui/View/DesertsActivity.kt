package com.example.cake_shop.ui.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityDesertsBinding

class DesertsActivity : AppCompatActivity() {

    private lateinit var dessertsActivityBinding : ActivityDesertsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dessertsActivityBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_deserts)


        dessertsActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }
    }
}