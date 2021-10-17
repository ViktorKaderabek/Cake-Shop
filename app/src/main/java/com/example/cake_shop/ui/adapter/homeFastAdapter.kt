package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentHomeBinding
import com.example.cake_shop.databinding.HomeItemsBinding
import com.example.cake_shop.model.HomeDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class homeFastAdapter (val homeItem: HomeDataClass) :
    AbstractBindingItem<HomeItemsBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: HomeItemsBinding, payloads: List<Any>) {

        binding.txtTopic.text = homeItem.title
        binding.txtSecondaryTopic.text = homeItem.secondaryTitle
        binding.imgLook.setImageResource(homeItem.image)

    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): HomeItemsBinding {
        return HomeItemsBinding.inflate(inflater, parent, false)
    }


}