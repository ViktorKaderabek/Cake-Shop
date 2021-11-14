package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemDessertsBinding
import com.example.cake_shop.model.data.DessertDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class DessertFastAdapter(val desertItem : DessertDataClass) ://Adapter
     AbstractBindingItem<ItemDessertsBinding>()
{
     
     override val type : Int
	get() = R.id.home_item
     
     override fun bindView(
         binding : ItemDessertsBinding,
         payloads : List<Any>,
                          )
     {//prirazuje view s DataClassou
	
	binding.txtNazev.text = desertItem.name
	binding.txtPopis.text = desertItem.popis
	binding.txtAlergeny.text = desertItem.alergeny
	binding.txtCena.text = desertItem.cena
	binding.imgMain.setImageBitmap(desertItem.picture)
     }
     
     override fun createBinding(
//Vytvari recyclerview
         inflater : LayoutInflater,
         parent : ViewGroup?,
                               ) : ItemDessertsBinding
     {
	return ItemDessertsBinding.inflate(inflater,
				     parent,
				     false)
     }
     
}