package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.EmployeeTextItemBinding
import com.example.cake_shop.databinding.TeamItemBinding
import com.example.cake_shop.model.data.EmployeeProfileDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class EmployeeProfileFastAdapter(val employeeProfiletItem: EmployeeProfileDataClass) :
    AbstractBindingItem<TeamItemBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: TeamItemBinding, payloads: List<Any>) {

        binding.txtName.text = employeeProfiletItem.name
        binding.txtPositions.text = employeeProfiletItem.position
        binding.txtDescription.text = employeeProfiletItem.description
        binding.imgPhoto.setImageResource(employeeProfiletItem.picture)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): TeamItemBinding {
        return TeamItemBinding.inflate(inflater, parent, false)
    }

}