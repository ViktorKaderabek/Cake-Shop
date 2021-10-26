package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityResetPasswordBinding
import java.sql.*

class ResetPasswordActivity : AppCompatActivity() {
    private val ip = "192.168.0.242" //ip addressa na ktere bezi server
    private val port = "1300" // cislo portu
    private val database = "User_DB" //nazev Databaze
    private val us = "test" //prihlasovaci udaje do Sql Serveru
    private val pass = "1234"//prihlasovaci udaje do Sql Serveru
    private val url = "jdbc:jtds:sqlserver://$ip:$port/$database" //url pres ktery se prihlasuje do dbs nemeni se!!
    private var connRes: String? = null
    private var connection: Connection? = null

    private var email: String? = null
    private var password: String? = null

    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_reset_password)

        val loginIntent: Intent =
            Intent(this, LogginActivity::class.java)

        binding.imbtnBack.setOnClickListener {
            startActivity(loginIntent)
            finish()
        }

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

        binding.btnResetPwd.setOnClickListener {

            if (binding.edtxEmail.text.isNotEmpty() &&//pokud jsou vsechna pole zaplnena tak se provede podminka
                binding.edtxPassword.text.isNotEmpty()

            ) {
                if (connRes == "SUCCESS") { //pokud se pripojeni k dbs zdarilo stane se podminka

                    email = binding.edtxEmail.text.toString()
                    password = binding.edtxPassword.text.toString()

                    var statement: Statement? = null
                    try {

                        statement = connection!!.createStatement()

                        var resultSet: ResultSet =
                            statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Users where email = ('$email');")//secte kolik je shod s danou email.adress
                        var result: String = ""

                        if (resultSet.next()) {
                            result = resultSet.getString(1)

                            if (result == "1") {
                                startActivity(loginIntent)
                                resultSet =
                                    statement.executeQuery("Update Users Set password = ('$password') where email = ('$email');")
                                Toast.makeText(
                                    applicationContext,
                                    "Password change successfuly",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else if (result == "0") {
                                Toast.makeText(
                                    applicationContext,
                                    "Incorrect email or password",
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