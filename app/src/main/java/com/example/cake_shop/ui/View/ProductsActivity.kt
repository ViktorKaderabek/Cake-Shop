package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityProductsBinding
import com.example.cake_shop.model.data.ProductsDataClass
import com.example.cake_shop.ui.ViewModel.ProductsViewModel
import com.example.cake_shop.ui.adapter.ProductsFastAdapter

@Suppress("DEPRECATION")
class ProductsActivity : AppCompatActivity() {

    private lateinit var productsActivityBinding: ActivityProductsBinding
    private lateinit var productViewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_products)

        productViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(ProductsViewModel::class.java)

        val cakesIntent =
            Intent(this, CakesActivity::class.java)
        val iceCreamIntent =
            Intent(this, IceCreamActivity::class.java)
        val dessertsIntent =
            Intent(this, DesertsActivity::class.java)

        productsActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        productsActivityBinding.recyclerview.adapter =
            productViewModel.fastAdapter // Nastavuje recyclerview co bude obsahem
        productsActivityBinding.recyclerview.setHasFixedSize(true)

        productsActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }

        productViewModel.itemAdapter.add(
            ProductsFastAdapter(
                ProductsDataClass(
                    "CAKES", R.drawable.cake
                )
            )
        )
        productViewModel.itemAdapter.add(
            ProductsFastAdapter(
                ProductsDataClass(
                    "ICE CREAMES", R.drawable.ice_creame
                )
            )
        )
        productViewModel.itemAdapter.add(
            ProductsFastAdapter(
                ProductsDataClass(
                    "DESSERTS", R.drawable.desert
                )
            )
        )

        productViewModel.fastAdapter.onClickListener = { view, adapter, item, position ->

            if (position == 0) {
                startActivityForResult(cakesIntent, 1)
            } else if (position == 1) {
                startActivityForResult(iceCreamIntent, 1)
            } else if (position == 2) {
                startActivityForResult(dessertsIntent, 1)
            }
            false
        }
    }
}