package com.example.cake_shop.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.HomeItemsBinding
import com.example.cake_shop.model.data.HomeDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class HomeFastAdapter(val homeItem: HomeDataClass) :
    AbstractBindingItem<HomeItemsBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: HomeItemsBinding, payloads: List<Any>) {



        binding.txtTopic.text = homeItem.title
        binding.txtSecondaryTopic.text = homeItem.secondaryTitle
        binding.imgMain.setImageResource(homeItem.mainImage)
        binding.imgSecondary.setImageResource(homeItem.secondaryImage)

    }
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): HomeItemsBinding {
        return HomeItemsBinding.inflate(inflater, parent, false)
    }

}