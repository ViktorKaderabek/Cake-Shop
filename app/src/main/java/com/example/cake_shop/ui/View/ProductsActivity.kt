package com.example.cake_shop.ui.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    private lateinit var productsActivityBinding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_products)

        productsActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }
    }
}