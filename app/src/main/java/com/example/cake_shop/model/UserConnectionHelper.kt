package com.example.cake_shop.model

import android.annotation.SuppressLint
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager

class UserConnectionHelper {

    private val connection: Connection? = null

    @SuppressLint("NewApi")

    fun getConnection(): Connection {

        var con: Connection? = null
        val ip: String = "192.168.0.242"
        val port: String = "1300"
        val db: String = "UserDB"
        val username: String = "test"
        val pass: String = "1234"
        val a: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        val connectURL: String? = null
        StrictMode.setThreadPolicy(a)

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            val connectURL: String =
                "jdbc:jtds:sqlserver://$ip:$port;databasename=$db;user=$username;password=$pass;"

            con = DriverManager.getConnection(connectURL)
            return con
        } catch (e: Exception) {
            Log.e("Error :", e.message.toString())
        }
        return con!!
    }
}