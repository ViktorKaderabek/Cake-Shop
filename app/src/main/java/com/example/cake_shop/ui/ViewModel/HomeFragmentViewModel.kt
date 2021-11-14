package com.example.cake_shop.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.cake_shop.ui.adapter.HomeFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class HomeFragmentViewModel : ViewModel()
{
     
     val itemAdapter =
	ItemAdapter<HomeFastAdapter>()
     val fastAdapter =
	FastAdapter.with(itemAdapter)
}