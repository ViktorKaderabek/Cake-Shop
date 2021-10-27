package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.EmployeeTextItemBinding
import com.example.cake_shop.model.data.EmployeeTextDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class EmployeeFastAdapter(val employeeProfiletItem: EmployeeTextDataClass) :
    AbstractBindingItem<EmployeeTextItemBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: EmployeeTextItemBinding, payloads: List<Any>) {

        binding.txtTopic.text = employeeProfiletItem.title
        binding.txtSecondaryTopic.text = employeeProfiletItem.secondaryTitle

    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): EmployeeTextItemBinding {
        return EmployeeTextItemBinding.inflate(inflater, parent, false)
    }

}