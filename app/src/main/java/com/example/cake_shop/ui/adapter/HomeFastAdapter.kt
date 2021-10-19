package com.example.cake_shop.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.HomeItemsBinding
import com.example.cake_shop.model.HomeDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import kotlin.random.Random

class HomeFastAdapter(val homeItem: HomeDataClass) :
    AbstractBindingItem<HomeItemsBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: HomeItemsBinding, payloads: List<Any>) {

        var number : Int = 0
        number += 1

        binding.txtTopic.text = homeItem.title
        binding.txtSecondaryTopic.text = homeItem.secondaryTitle
        binding.imgLook.setImageResource(homeItem.image)

        if (number == 1) {
            binding.background.setBackgroundColor(Color.parseColor("#a5f2a2"))
        } else if (number == 2) {
            binding.background.setBackgroundColor(Color.parseColor("#ed8c9f"))
        } else if (number == 3) {
            number = 0
            binding.background.setBackgroundColor(Color.parseColor("#949191"))
        }
    }
    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): HomeItemsBinding {
        return HomeItemsBinding.inflate(inflater, parent, false)
    }

}