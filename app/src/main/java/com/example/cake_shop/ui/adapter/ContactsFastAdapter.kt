package com.example.cake_shop.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ContactsItemBinding
import com.example.cake_shop.databinding.HomeItemsBinding
import com.example.cake_shop.model.data.ContactsDataClass
import com.example.cake_shop.model.data.HomeDataClass
import com.example.cake_shop.ui.View.fragments.ContactsFragment
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ContactsFastAdapter(val contactsItem: ContactsDataClass) :
    AbstractBindingItem<ContactsItemBinding>() {

    override val type: Int
        get() = R.id.home_item

    override fun bindView(binding: ContactsItemBinding, payloads: List<Any>) {

        var number : Int = 0
        number += 1

        binding.icLocation.setImageResource(contactsItem.location_img)
        binding.icMail.setImageResource(contactsItem.mail_img)
        binding.icPhone.setImageResource(contactsItem.phone_img)
        binding.txtLocationTopic.text = contactsItem.location_topic
        binding.txtAddresse.text = contactsItem.location_addresse
        binding.txtPhoneTopic.text = contactsItem.phone_topic
        binding.txtPhoneNumber.text = contactsItem.phone_addresse
        binding.txtMailTopic.text = contactsItem.mail_topic
        binding.txtMailAddresse.text = contactsItem.mail_addresse





    }
    override fun createBinding( inflater: LayoutInflater, parent: ViewGroup?): ContactsItemBinding {




        return ContactsItemBinding.inflate(inflater, parent, false)
    }

}