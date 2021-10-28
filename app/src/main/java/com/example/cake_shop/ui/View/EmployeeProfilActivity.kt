package com.example.cake_shop.ui.View

import android.graphics.Bitmap
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityEmployeeProfilBinding
import com.example.cake_shop.model.data.EmployeeProfileDataClass
import com.example.cake_shop.ui.adapter.EmployeeProfileFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import java.sql.*

class EmployeeProfilActivity : AppCompatActivity() {

    private val ip = "192.168.0.242" //ip addressa na ktere bezi server
    private val port = "1300" // cislo portu
    private val database = "CakeShopDB" //nazev Databaze
    private val us = "test" //prihlasovaci udaje do Sql Serveru
    private val pass = "1234"//prihlasovaci udaje do Sql Serveru
    private val url =
        "jdbc:jtds:sqlserver://$ip:$port/$database" //url pres ktery se prihlasuje do dbs nemeni se!!
    private var connRes: String? = null
    private var connection: Connection? = null

    private var count: Int = 1
    private var idCount: Int = 0
    private var name: String? = null
    private var position: String? = null
    private var description: String? = null

    private lateinit var employeeProfileActivityBinding: ActivityEmployeeProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeProfileActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee_profil)


        val itemAdapter =
            ItemAdapter<EmployeeProfileFastAdapter>()
        val fastAdapter =
            FastAdapter.with(itemAdapter)

        employeeProfileActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        employeeProfileActivityBinding.recyclerview.adapter =
            fastAdapter // Nastavuje recyclerview co bude obsahem
        employeeProfileActivityBinding.recyclerview.setHasFixedSize(true)

        employeeProfileActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
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

        if (connRes == "SUCCESS") { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null
            try {
                statement = connection!!.createStatement()
                var query1: ResultSet =
                    statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM EmployeeInfo")
                if (query1.next()) {
                    count = query1.getInt(1)
                    for (i in 1..count) {
                        idCount += 1
                        query1 =
                            statement.executeQuery("select name from EmployeeInfo where id = ('$idCount')")
                        if (query1.next()) {
                            name = query1.getString(1)
                            query1 =
                                statement.executeQuery("select position from EmployeeInfo where id = ('$idCount')")
                            if (query1.next()) {
                                position = query1.getString(1)
                                query1 =
                                    statement.executeQuery("select description from EmployeeInfo where id = ('$idCount')")
                                if (query1.next()) {
                                    description = query1.getString(1)
                                }
                            }
                        }

                        itemAdapter.add(
                            EmployeeProfileFastAdapter(
                                EmployeeProfileDataClass(
                                    name.toString(),
                                    position.toString(),
                                    description.toString(),
                                    R.drawable.ic_baseline_face_24
                                )
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                Log.e("Error:", e.message.toString())
            }
        }
    }
}