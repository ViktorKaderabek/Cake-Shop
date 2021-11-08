package com.example.cake_shop.ui.View.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentContactsBinding
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.ContactsFragmentViewModel
import java.sql.SQLException
import java.sql.Statement
import android.view.View as View1


@Suppress("DEPRECATION")
class ContactsFragment : Fragment() {

    private lateinit var contactsFragmentViewModel: ContactsFragmentViewModel
    private lateinit var contactsFragmentBinding: FragmentContactsBinding
    private val connect = ConnectionHelper().getConnection()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View1 {

        contactsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        val view: View1 = contactsFragmentBinding.root

        contactsFragmentViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(ContactsFragmentViewModel::class.java)

        if (connect != null) {

            val statement: Statement?

            try {
                statement = connect!!.createStatement()
                var query1 =
                    statement.executeQuery("Select email from cakeShopContacts where id = ('1') ")
                if (query1.next()) {

                    contactsFragmentViewModel.email = query1.getString(1)
                    query1 =
                        statement.executeQuery("Select locAddress from cakeShopContacts where id = ('1') ")

                    if (query1.next()) {

                        contactsFragmentViewModel.address = query1.getString(1)
                        query1 =
                            statement.executeQuery("Select phoneNumber from cakeShopContacts where id = ('1') ")

                        if (query1.next()) {
                            contactsFragmentViewModel.phone = query1.getString(1)
                        }
                    }
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        contactsFragmentBinding.txtMailAddresse.text = contactsFragmentViewModel.email
        contactsFragmentBinding.txtAddresse.text = contactsFragmentViewModel.address
        contactsFragmentBinding.txtPhoneNumber.text = contactsFragmentViewModel.phone

        contactsFragmentBinding.txtMailAddresse.setOnClickListener {
            val emailIntent =
                Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", contactsFragmentViewModel.email, null)
                )
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        contactsFragmentBinding.txtAddresse.setOnClickListener {
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:50.0654, 14.3035?q=50.0654,14.3035 (Label+Name)")
            )
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        contactsFragmentBinding.txtPhoneNumber.setOnClickListener {
            val contactsIntent =
                Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI)
            contactsIntent.putExtra("phone", contactsFragmentViewModel.phone)
            contactsIntent.putExtra("name", "CakeShop.cz")
            contactsIntent.putExtra("email", contactsFragmentViewModel.email)
            contactsIntent.putExtra("address", contactsFragmentViewModel.address)

            startActivity(contactsIntent)

        }

        return view
    }


}