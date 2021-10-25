package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityMainBinding
import java.sql.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val loginIntent: Intent =
            Intent(this, LogginActivity::class.java)
        val signUpIntent: Intent =
            Intent(this, SignUpActivity::class.java)
        val cakeShopIntent: Intent =
            Intent(this, CakeShopActivity::class.java)

        binding.btnSignup.setOnClickListener {
            startActivityForResult(signUpIntent, 1)
        }

        binding.btnLogin.setOnClickListener {
            startActivityForResult(loginIntent, 1)
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





        if (connRes == "SUCCESS") { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null
            try {

                statement = connection!!.createStatement()

                var query1: ResultSet =
                    statement.executeQuery("Select state from EmailHolder where id = ('1')")
                var result1 : String? = null
                if (query1.next()) {

                    result1 = query1.getString(1)
                    if (result1 == "true") {
                        startActivity(cakeShopIntent)
                    }
                }


            } catch (e: Exception) {
                Log.e("Error :", e.message.toString())
            }
        }
    }
}