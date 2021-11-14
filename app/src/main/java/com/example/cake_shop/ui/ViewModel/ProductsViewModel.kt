package com.example.cake_shop.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.cake_shop.ui.adapter.ProductsFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class ProductsViewModel : ViewModel()
{
     
     val itemAdapter =
	ItemAdapter<ProductsFastAdapter>()
     val fastAdapter =
	FastAdapter.with(itemAdapter)
}