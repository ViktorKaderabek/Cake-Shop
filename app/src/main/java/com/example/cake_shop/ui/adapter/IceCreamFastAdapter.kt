package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemIceCreamBinding
import com.example.cake_shop.model.data.IceCreamsDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class IceCreamFastAdapter(val iceCreamItem : IceCreamsDataClass) ://Adapter
     AbstractBindingItem<ItemIceCreamBinding>()
{
     
     override val type : Int
	get() = R.id.home_item
     
     override fun bindView(
         binding : ItemIceCreamBinding,
         payloads : List<Any>,
                          )
     {//prirazuje view s DataClassou
	
	binding.txtNazev.text = iceCreamItem.name
	binding.txtPopis.text = iceCreamItem.popis
	binding.txtAlergeny.text = iceCreamItem.alergeny
	binding.txtCena.text = iceCreamItem.cena
	binding.imgMain.setImageBitmap(iceCreamItem.picture)
	
     }
     
     override fun createBinding(
//Vytvari recyclerview
         inflater : LayoutInflater,
         parent : ViewGroup?,
                               ) : ItemIceCreamBinding
     {
	return ItemIceCreamBinding.inflate(inflater,
				     parent,
				     false)
     }
     
}