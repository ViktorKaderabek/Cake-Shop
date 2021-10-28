package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemEmployeeBinding
import com.example.cake_shop.model.data.EmployeeProfileDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class EmployeeProfileFastAdapter(val employeeProfiletItem: EmployeeProfileDataClass) :
    AbstractBindingItem<ItemEmployeeBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: ItemEmployeeBinding, payloads: List<Any>) {

        binding.txtName.text = employeeProfiletItem.name
        binding.txtPositions.text = employeeProfiletItem.position
        binding.txtDescription.text = employeeProfiletItem.description
        binding.imgPhoto.setImageBitmap(employeeProfiletItem.picture)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEmployeeBinding {
        return ItemEmployeeBinding.inflate(inflater, parent, false)
    }

}