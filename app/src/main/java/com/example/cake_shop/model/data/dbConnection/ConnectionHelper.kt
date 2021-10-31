package com.example.cake_shop.model.data.dbConnection

import android.annotation.SuppressLint
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionHelper {


    private var connection: Connection? = null

    @SuppressLint("NewApi")

    fun getConnection(): Connection {

        val ip = "192.168.0.242" //ip addressa na ktere bezi server
        val port = "1300" // cislo portu
        val database = "CakeShopDB" //nazev Databaze
        val us = "test" //prihlasovaci udaje do Sql Serveru
        val pass = "1234"//prihlasovaci udaje do Sql Serveru
        val url =
            "jdbc:jtds:sqlserver://$ip:$port/$database" //url pres ktery se prihlasuje do dbs nemeni se!!

        val policy: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        try {//zkousi se pripojit do me dbs
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connection = DriverManager.getConnection(url, us, pass)
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()
            Log.e("Error :", exception.message.toString())
        } catch (exception: SQLException) {
            exception.printStackTrace()
            Log.e("Error :", exception.message.toString())
        }

        return connection!!

    }

}