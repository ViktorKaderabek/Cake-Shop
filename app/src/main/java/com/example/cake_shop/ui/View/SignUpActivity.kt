package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivitySignupBinding
import com.example.cake_shop.ui.ViewModel.SignUpViewModel
import java.sql.*

class SignUpActivity : AppCompatActivity() {
    private val ip = "192.168.0.242"
    private val port = "1300"
    private val database = "User_DB"
    private val us = "test"
    private val pass = "1234"
    private val url = "jdbc:jtds:sqlserver://$ip:$port/$database"
    private var connRes: String? = null
    private var connection: Connection? = null

    private var username: String? = null
    private var email: String? = null
    private var password: String? = null
    private var name: String? = null

    private lateinit var signUpViwModel: SignUpViewModel
    private lateinit var binding: ActivitySignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val mainActivityIntent: Intent =
            Intent(this, MainActivity::class.java)
        val logginIntent: Intent =
            Intent(this, LogginActivity::class.java)

        binding.imbtnBack.setOnClickListener {
            setResult(0, mainActivityIntent)
            finish()
        }
        binding.btnAlreadyAcc.setOnClickListener {
            startActivity(logginIntent)
            finish()
        }

        val policy: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connection = DriverManager.getConnection(url, us, pass)
            connRes = "SUCCESS"
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()
            connRes = "ERROR"
            Log.e("Error :", exception.message.toString())
        } catch (exception: SQLException) {
            exception.printStackTrace()
            connRes = "FAILURE"
            Log.e("Error :", exception.message.toString())
        }
        binding.btnSignup.setOnClickListener {

            if (binding.edtxEmail.text.isNotEmpty() &&
                binding.edtxUserName.text.isNotEmpty() &&
                binding.edtxPassword.text.isNotEmpty() &&
                binding.edtxFullname.text.isNotEmpty()
            ) {
                if (connRes == "SUCCESS") {

                    email = binding.edtxEmail.text.toString()
                    username = binding.edtxUserName.text.toString()
                    password = binding.edtxPassword.text.toString()
                    name = binding.edtxFullname.text.toString()

                    var statement: Statement? = null
                    try {

                        statement = connection!!.createStatement()

                        var resultSet: ResultSet =
                            statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Users where email = ('$email')")
                        var result: String = ""

                        if (resultSet.next()) {
                            result = resultSet.getString(1)

                            if (result == "0") {
                                startActivity(logginIntent)
                                resultSet =
                                    statement.executeQuery("Insert into Users(email,username,name,password) VALUES ('$email','$username','$name','$password');")
                                Toast.makeText(
                                    applicationContext,
                                    "Successful Sign Up",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }else{
                                Toast.makeText(
                                    applicationContext,
                                    "User Already Exists",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }

                    } catch (e: SQLException) {
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(applicationContext, "Connection is null", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
