package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.CakesItemBinding
import com.example.cake_shop.databinding.ProductsItemsBinding
import com.example.cake_shop.model.data.CakesDataClass
import com.example.cake_shop.model.data.ProductsDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CakesFastAdapter(val cakeItem: CakesDataClass) :
    AbstractBindingItem<CakesItemBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: CakesItemBinding, payloads: List<Any>) {

        binding.txtNazev.text = cakeItem.name
        binding.txtPopis.text = cakeItem.popis
        binding.txtAlergeny.text = cakeItem.alergeny
        binding.txtCena.text = cakeItem.cena
        binding.imgMain.setImageBitmap(cakeItem.picture)

    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): CakesItemBinding {
        return CakesItemBinding.inflate(inflater, parent, false)
    }

}