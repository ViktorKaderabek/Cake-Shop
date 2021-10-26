package com.example.cake_shop.ui.View.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentContactsBinding
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement
import android.view.View as View1


@Suppress("DEPRECATION")
class ContactsFragment : Fragment() {

    private val ip = "192.168.0.242" //ip addressa na ktere bezi server
    private val port = "1300" // cislo portu
    private val database = "User_DB" //nazev Databaze
    private val us = "test" //prihlasovaci udaje do Sql Serveru
    private val pass = "1234"//prihlasovaci udaje do Sql Serveru
    private val url =
        "jdbc:jtds:sqlserver://$ip:$port/$database" //url pres ktery se prihlasuje do dbs nemeni se!!
    private var connRes: String? = null
    private var connection: Connection? = null

    private var email: String? = null
    private var address: String? = null
    private var phone: String? = null

    private lateinit var contactsFragmentBinding: FragmentContactsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View1 {

        contactsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        val view: View1 = contactsFragmentBinding.root
        val policy: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        try {//zkousi se pripojit do me dbs
            Class.forName("net.sourceforge.jtds.jdbc.Driver")//nemeni se
            connection = DriverManager.getConnection(url, us, pass)//zadava url a prihlasovaci udaje
            connRes = "SUCCESS" // nastavuje vysledek jako uspescny
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()
            connRes = "ERROR"// nastavuje vysledek jako neuspesny
            Log.e("Error :", exception.message.toString())
        } catch (exception: SQLException) {
            exception.printStackTrace()
            connRes = "FAILURE"
            Log.e("Error :", exception.message.toString())
        }

        if (connRes == "SUCCESS") { //pokud se pripojeni k dbs zdarilo stane se podminka

            val statement: Statement?

            try {
                statement = connection!!.createStatement()
                var query1 =
                    statement.executeQuery("Select email from cakeShopContacts where id = ('1') ")
                if (query1.next()) {

                    email = query1.getString(1)
                    query1 =
                        statement.executeQuery("Select locAddress from cakeShopContacts where id = ('1') ")

                    if (query1.next()) {

                        address = query1.getString(1)
                        query1 =
                            statement.executeQuery("Select phoneNumber from cakeShopContacts where id = ('1') ")

                        if (query1.next()) {
                            phone = query1.getString(1)
                        }
                    }
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        contactsFragmentBinding.txtMailAddresse.text = email
        contactsFragmentBinding.txtAddresse.text = address
        contactsFragmentBinding.txtPhoneNumber.text = phone

        contactsFragmentBinding.txtMailAddresse.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null))
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
            contactsIntent.putExtra("phone", phone)
            contactsIntent.putExtra("name", "CakeShop.cz")
            contactsIntent.putExtra("email", email)
            contactsIntent.putExtra("address", address)

            startActivity(contactsIntent)

        }

        return view
    }


}