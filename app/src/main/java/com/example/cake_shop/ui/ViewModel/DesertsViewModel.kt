package com.example.cake_shop.ui.ViewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.cake_shop.ui.adapter.DessertFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class DesertsViewModel : ViewModel()
{
     
     var count : Int = 1
     var bitmapImageDB : Bitmap? = null
     var photo : ByteArray? = null
     var idCount : Int = 0
     var name : String? = null
     var popis : String? = null
     var alergeny : String? = null
     var cena : String? = null
     
     val itemAdapter =
	ItemAdapter<DessertFastAdapter>()
     val fastAdapter =
	FastAdapter.with(itemAdapter)
}