package com.example.cake_shop.ui.View

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityDesertsBinding
import com.example.cake_shop.model.data.CakesDataClass
import com.example.cake_shop.model.data.DessertDataClass
import com.example.cake_shop.ui.adapter.CakesFastAdapter
import com.example.cake_shop.ui.adapter.DessertFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import java.sql.*

class DesertsActivity : AppCompatActivity() {

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
    var bitmapImageDB: Bitmap? = null
    private var photo: ByteArray? = null
    private var idCount: Int = 0
    private var name: String? = null
    private var popis: String? = null
    private var alergeny: String? = null
    private var cena: String? = null

    private lateinit var dessertsActivityBinding: ActivityDesertsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dessertsActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_deserts)


        val itemAdapter =
            ItemAdapter<DessertFastAdapter>()
        val fastAdapter =
            FastAdapter.with(itemAdapter)

        dessertsActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        dessertsActivityBinding.recyclerview.adapter =
            fastAdapter // Nastavuje recyclerview co bude obsahem
        dessertsActivityBinding.recyclerview.setHasFixedSize(true)

        dessertsActivityBinding.imbtnBack.setOnClickListener {
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
                    statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM dezerty")
                if (query1.next()) {
                    count = query1.getInt(1)
                    for (i in 1..count) {
                        idCount += 1
                        query1 =
                            statement.executeQuery("select nazev from dezerty where id = ('$idCount')")
                        if (query1.next()) {
                            name = query1.getString(1)
                            query1 =
                                statement.executeQuery("select popis from dezerty where id = ('$idCount')")
                            if (query1.next()) {
                                popis = query1.getString(1)
                                query1 =
                                    statement.executeQuery("select alergeny from dezerty where id = ('$idCount')")
                                if (query1.next()) {
                                    alergeny = query1.getString(1)
                                    query1 =
                                        statement.executeQuery("select cena from dezerty where id = ('$idCount')")
                                    if (query1.next()) {
                                        cena = query1.getString(1)
                                        query1 =
                                            statement.executeQuery("select photo from dezerty where id = ('$idCount')")
                                        if (query1.next()) {
                                            photo = query1.getBytes(1)
                                            bitmapImageDB = BitmapFactory.decodeByteArray(
                                                photo,
                                                0,
                                                photo!!.size
                                            )

                                        }
                                    }
                                }
                            }
                        }
                        itemAdapter.add(
                            DessertFastAdapter(
                                DessertDataClass(
                                    "  " + name.toString(),
                                    "  " + popis.toString(),
                                    "  " + alergeny.toString(),
                                    "  " + cena.toString(),
                                    bitmapImageDB!!
                                )
                            )
                        )
                    }

                }

            } catch (exception: SQLException) {

            }

        }
    }
}