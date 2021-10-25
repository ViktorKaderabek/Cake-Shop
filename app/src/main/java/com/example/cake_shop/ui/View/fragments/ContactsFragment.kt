package com.example.cake_shop.ui.View.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentContactsBinding
import com.example.cake_shop.model.data.ContactsDataClass
import com.example.cake_shop.ui.adapter.ContactsFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.IItem

import com.mikepenz.fastadapter.IAdapter





@Suppress("DEPRECATION")
class ContactsFragment : Fragment() {

    private lateinit var contactsFragmentBinding: FragmentContactsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        contactsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        val view: View = contactsFragmentBinding.root



        return view
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        contactsFragmentBinding.recyclerview.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            val itemAdapter =
                ItemAdapter<ContactsFastAdapter>()

            val fastAdapter =
                FastAdapter.with(itemAdapter) //promenna ktera v sobe uchovava udaje o tom co je v adapteru

            var position: Int = 0

            contactsFragmentBinding.recyclerview.layoutManager =
                LinearLayoutManager(context)
            contactsFragmentBinding.recyclerview.adapter =
                fastAdapter // Nastavuje recyclerview co bude obsahem
            contactsFragmentBinding.recyclerview.setHasFixedSize(true)

            itemAdapter.add(
                ContactsFastAdapter(
                    ContactsDataClass(
                        R.drawable.ic_baseline_location_on_24,
                        "OUR MAIN OFFICE",
                        "15, Makovského 1227, 163 00 Praha 17",
                        R.drawable.ic_baseline_local_phone_24,
                        "PHONE NUMBER",
                        "+420 605 058 155",
                        R.drawable.ic_baseline_mail_24,
                        "MAIL ADDRESSE",
                        "info@CakeShop.cz"
                    )
                )
            )



        }


    }

}