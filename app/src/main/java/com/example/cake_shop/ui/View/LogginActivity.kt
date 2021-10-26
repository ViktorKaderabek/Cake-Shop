package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityLogginBinding
import com.example.cake_shop.ui.ViewModel.LoginViewModel
import java.sql.*

class LogginActivity : AppCompatActivity() {
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
    private var password: String? = null


    private lateinit var logginViewModel: LoginViewModel //Vytvarim promennou, ktera odkazuje na ViewModel pro tuto tridu
    private lateinit var logginBinding: ActivityLogginBinding
    //Vytvarim promennou, ktera odkazuje na fragment com.example.cake_shop.ui.View.fragments.AccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_loggin)

        val mainActivityIntent: Intent =
            Intent(this, MainActivity::class.java)
        val resetPasswordIntent: Intent =
            Intent(this, ResetPasswordActivity::class.java)
        val cakeShopIntent: Intent =
            Intent(this, CakeShopActivity::class.java)



        logginBinding.imbtnBack.setOnClickListener {
            setResult(0, mainActivityIntent)
            finish()
        }

        logginBinding.btnResetPwd.setOnClickListener {
            startActivity(resetPasswordIntent)
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
        logginBinding.btnLogin.setOnClickListener {

            if (logginBinding.edtxEmail.text.isNotEmpty() &&//pokud jsou vsechna pole zaplnena tak se provede podminka
                logginBinding.edtxPassword.text.isNotEmpty()

            ) {
                if (connRes == "SUCCESS") { //pokud se pripojeni k dbs zdarilo stane se podminka

                    email = logginBinding.edtxEmail.text.toString()
                    password = logginBinding.edtxPassword.text.toString()

                    var statement: Statement? = null
                    try {

                        statement = connection!!.createStatement()

                        var query1: ResultSet =
                            statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Users where email = ('$email')")
                        var result1: String = ""


                        if (query1.next()) {
                            result1 = query1.getString(1)

                            if (result1 == "1") {
                                startActivity(cakeShopIntent)
                                query1 =
                                    statement.executeQuery("Update EmailHolder Set email = ('$email'),state = ('true') where id = ('1');")
                                Toast.makeText(
                                    applicationContext,
                                    "Successful Log In",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else if (result1 == "0") {
                                query1 =
                                    statement.executeQuery("Update EmailHolder Set state = ('false') where id = ('1');")
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