package com.example.cake_shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ItemCakesBinding
import com.example.cake_shop.model.data.CakesDataClass
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CakesFastAdapter(val cakeItem : CakesDataClass) ://Adapter
     AbstractBindingItem<ItemCakesBinding>()
{
     
     override val type : Int
	get() = R.id.home_item
     
     override fun bindView(
         binding : ItemCakesBinding,
         payloads : List<Any>,
                          )
     {//prirazuje view s DataClassou
	
	binding.txtNazev.text = cakeItem.name
	binding.txtPopis.text = cakeItem.popis
	binding.txtAlergeny.text = cakeItem.alergeny
	binding.txtCena.text = cakeItem.cena
	binding.imgMain.setImageBitmap(cakeItem.picture)
	
     }
     
     override fun createBinding(
//Vytvari recyclerview
         inflater : LayoutInflater,
         parent : ViewGroup?,
                               ) : ItemCakesBinding
     {
	return ItemCakesBinding.inflate(inflater,
				  parent,
				  false)
     }
     
}