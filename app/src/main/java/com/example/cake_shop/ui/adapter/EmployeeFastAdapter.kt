package com.example.cake_shop.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.EmployeeTextItemBinding
import com.example.cake_shop.databinding.HomeItemsBinding
import com.example.cake_shop.model.data.EmployeeTextDataClass
import com.example.cake_shop.model.data.HomeDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class EmployeeFastAdapter(val employeeItem: EmployeeTextDataClass) :
    AbstractBindingItem<EmployeeTextItemBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: EmployeeTextItemBinding, payloads: List<Any>) {



        binding.txtTopic.text = employeeItem.title
        binding.txtSecondaryTopic.text = employeeItem.secondaryTitle


    }
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): EmployeeTextItemBinding {
        return EmployeeTextItemBinding.inflate(inflater, parent, false)
    }

}