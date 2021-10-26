package com.example.cake_shop.ui.View.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentContactsBinding


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


        contactsFragmentBinding.txtMailAddresse.setOnClickListener {
            val emailIntent: Intent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@CakeShop.cz", null))
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        contactsFragmentBinding.txtAddresse.setOnClickListener {
            val mapIntent: Intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:50.0654, 14.3035?q=50.0654,14.3035 (Label+Name)")
            )
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        contactsFragmentBinding.txtPhoneNumber.setOnClickListener {
            val contactsIntent: Intent =
                Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI)
            contactsIntent.putExtra("phone", "420 605 058 155");
            contactsIntent.putExtra("name", "CakeShop.cz");
            contactsIntent.putExtra("email", "info@CakeShop.cz");
            contactsIntent.putExtra("address", "Makovského 1227/15, 163 00 Praha 17-Řepy");

            startActivity(contactsIntent)

        }

        return view
    }


}