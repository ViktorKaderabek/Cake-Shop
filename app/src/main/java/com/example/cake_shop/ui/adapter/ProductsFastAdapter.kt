package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemProductBinding
import com.example.cake_shop.model.data.ProductsDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ProductsFastAdapter(val homeItem: ProductsDataClass) :
    AbstractBindingItem<ItemProductBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: ItemProductBinding, payloads: List<Any>) {

        binding.txtTopic.text = homeItem.title
        binding.imgMain.setImageResource(homeItem.image)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemProductBinding {
        return ItemProductBinding.inflate(inflater, parent, false)
    }

}