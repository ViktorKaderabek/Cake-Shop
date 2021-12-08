package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemEmployeeTextBinding
import com.example.cake_shop.model.data.EmployeeTextDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class EmployeeFastAdapter(val employeeTexttItem: EmployeeTextDataClass) ://Adapter
    AbstractBindingItem<ItemEmployeeTextBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(
        binding: ItemEmployeeTextBinding,
        payloads: List<Any>,
    ) {//prirazuje view s DataClassou
        binding.txtTopic.text = employeeTexttItem.title
        binding.txtSecondaryTopic.text = employeeTexttItem.secondaryTitle
    }

    override fun createBinding(
//Vytvari recyclerview
        inflater: LayoutInflater,
        parent: ViewGroup?,
    ): ItemEmployeeTextBinding {
        return ItemEmployeeTextBinding.inflate(
            inflater,
            parent,
            false
        )
    }

}