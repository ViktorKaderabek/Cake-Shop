package com.example.cake_shop.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.cake_shop.ui.adapter.AboutUsFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AboutUsViewModel : ViewModel() {

    val itemAdapter =
        ItemAdapter<AboutUsFastAdapter>() //Promena ktera v sobe ma list veci ktere jsou v adapteru

    val fastAdapter =
        FastAdapter.with(itemAdapter)//jenom rika co je adaptereme a zobrazi vsechny itemy ktere jsou v promenne itemAdapter




}