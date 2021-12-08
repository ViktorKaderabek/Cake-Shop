package com.example.cake_shop.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.cake_shop.ui.adapter.EmployeeFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class EmployeeViewModel : ViewModel() {

    val itemAdapter =
        ItemAdapter<EmployeeFastAdapter>()
    val fastAdapter =
        FastAdapter.with(itemAdapter)
}