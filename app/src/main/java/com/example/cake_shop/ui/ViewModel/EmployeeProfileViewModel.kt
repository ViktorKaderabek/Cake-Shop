package com.example.cake_shop.ui.ViewModel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.cake_shop.ui.adapter.EmployeeProfileFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class EmployeeProfileViewModel : ViewModel()
{
     
     var count : Int = 1
     var bitmapImageDB : Bitmap? = null
     var photo : ByteArray? = null
     var idCount : Int = 0
     var name : String? = null
     var position : String? = null
     var description : String? = null
     
     val itemAdapter =
	ItemAdapter<EmployeeProfileFastAdapter>()
     val fastAdapter =
	FastAdapter.with(itemAdapter)
}