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

        val ip = "192.168.0.242" //ip localhosota
        val port = "1300" //port localhosta
        val database = "CakeShopDB" //nazev DB
        val us = "test" // username k DB
        val pass = "1234" // heslo k DB
        val url =
            "jdbc:jtds:sqlserver://$ip:$port/$database"

        val policy: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        try { //zkousi se pripojit k DB
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connection = DriverManager.getConnection(url, us, pass)
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()
            Log.e("Error :", exception.message.toString())
        } catch (exception: SQLException) {
            exception.printStackTrace()
            Log.e("Error :", exception.message.toString())
        }

        return connection!! //pokud se pripoji tak vrati pripojeni
    }
}