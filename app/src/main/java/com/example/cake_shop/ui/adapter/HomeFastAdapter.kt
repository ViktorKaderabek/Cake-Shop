package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemHomeBinding
import com.example.cake_shop.model.data.HomeDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class HomeFastAdapter(val homeItem: HomeDataClass) ://Adapter
    AbstractBindingItem<ItemHomeBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(
        binding: ItemHomeBinding,
        payloads: List<Any>,
    ) {//prirazuje view s DataClassou

        binding.txtTopic.text = homeItem.title
        binding.txtSecondaryTopic.text = homeItem.secondaryTitle
        binding.imgMain.setImageResource(homeItem.mainImage)
        binding.imgSecondary.setImageResource(homeItem.secondaryImage)

    }

    override fun createBinding(
//Vytvari recyclerview
        inflater: LayoutInflater,
        parent: ViewGroup?,
    ): ItemHomeBinding {
        return ItemHomeBinding.inflate(
            inflater,
            parent,
            false
        )
    }

}