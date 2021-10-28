package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemAboutUsBinding
import com.example.cake_shop.model.data.AboutUsDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class AboutUsFastAdapter(val aboutUsItem: AboutUsDataClass) :
    AbstractBindingItem<ItemAboutUsBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: ItemAboutUsBinding, payloads: List<Any>) {

        binding.txtTopic.text = aboutUsItem.title
        binding.txtText.text = aboutUsItem.text
        binding.imgPhoto.setImageResource(aboutUsItem.image)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemAboutUsBinding {
        return ItemAboutUsBinding.inflate(inflater, parent, false)
    }

}