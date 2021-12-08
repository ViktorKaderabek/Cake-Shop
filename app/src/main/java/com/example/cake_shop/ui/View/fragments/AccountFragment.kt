package com.example.cake_shop.ui.View.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentAccountBinding
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.View.MainActivity
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

@Suppress("DEPRECATION")
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding //itemy z layoutu
    private val connect =
        ConnectionHelper().getConnection() //promenna ktera obsahuje odkaz na funcki v ConnectionHelper classe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_account,
            container,
            false
        )
        val view = binding.root
        val intent = Intent(
            activity,
            MainActivity::class.java
        )

        binding.btnEditProfilePicture.setOnClickListener { //kdyz se klikne ne button, tak se stane to co je v tele
            val galleryIntent = Intent()
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            galleryIntent.type = "image/*"
            startActivityForResult(
                Intent.createChooser(
                    galleryIntent,
                    "Pick an image"
                ),
                123
            )
        }

        binding.btnSignOut.setOnClickListener { //kdyz se klikne na btnSignOut
            startActivity(intent) //zapne se predem definovana aktivita ktera je pod promennou intent
            try {
                if (connect != null) { //pokud funkce connect neco vraci tak se stane to co je v tele

                    val statement2: Statement = connect!!.createStatement()
                    statement2.executeQuery("Update EmailHolder Set state = ('NULL'),email = ('NULL') where id = ('1');")
                }
            } catch (e: Exception) {

                Log.e(
                    "Error :",
                    e.message.toString()
                )
            }
        }

        if (connect != null) { //pokud funkce connect neco vraci tak se stane to co je v tele

            val statement: Statement?

            try {
                statement = connect!!.createStatement()
                val query1: ResultSet =
                    statement.executeQuery("Select email from EmailHolder where id = ('1')")
                if (query1.next()) {
                    val result: String = query1.getString(1)

                    var query2: ResultSet =
                        statement.executeQuery("Select email from Users where email = ('$result')")

                    if (query2.next()) {
                        binding.textView3.text = query2.getString(1)
                        query2 =
                            statement.executeQuery("Select name from Users where email = ('$result')")

                        if (query2.next()) {
                            binding.textView2.text = query2.getString(1)
                        }
                    }
                }

            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }

        return view
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            val imageData: Uri? = data.data
            binding.imProfilePicture.setImageURI(imageData)
        }
    }
}