package com.example.cake_shop.ui.View

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityIceCreamBinding
import com.example.cake_shop.model.data.IceCreamsDataClass
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.IceCreamViewModel
import com.example.cake_shop.ui.adapter.IceCreamFastAdapter
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class IceCreamActivity : AppCompatActivity() {

    private lateinit var iceCreamActivityBinding: ActivityIceCreamBinding
    private lateinit var iceCreamViewModel: IceCreamViewModel
    private val connect = ConnectionHelper().getConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iceCreamActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_ice_cream)

        iceCreamViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(IceCreamViewModel::class.java)


        iceCreamActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        iceCreamActivityBinding.recyclerview.adapter =
            iceCreamViewModel.fastAdapter // Nastavuje recyclerview co bude obsahem
        iceCreamActivityBinding.recyclerview.setHasFixedSize(true)

        iceCreamActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }

        if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null
            try {
                statement = connect!!.createStatement()
                var query1: ResultSet =
                    statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM zmrzliny")
                if (query1.next()) {
                    iceCreamViewModel.count = query1.getInt(1)
                    for (i in 1..iceCreamViewModel.count) {
                        iceCreamViewModel.idCount += 1
                        query1 =
                            statement.executeQuery("select nazev from zmrzliny where id = ('${iceCreamViewModel.idCount}')")
                        if (query1.next()) {
                            iceCreamViewModel.name = query1.getString(1)
                            query1 =
                                statement.executeQuery("select popis from zmrzliny where id = ('${iceCreamViewModel.idCount}')")
                            if (query1.next()) {
                                iceCreamViewModel.popis = query1.getString(1)
                                query1 =
                                    statement.executeQuery("select alergeny from zmrzliny where id = ('${iceCreamViewModel.idCount}')")
                                if (query1.next()) {
                                    iceCreamViewModel.alergeny = query1.getString(1)
                                    query1 =
                                        statement.executeQuery("select cena from zmrzliny where id = ('${iceCreamViewModel.idCount}')")
                                    if (query1.next()) {
                                        iceCreamViewModel.cena = query1.getString(1)
                                        query1 =
                                            statement.executeQuery("select photo from zmrzliny where id = ('${iceCreamViewModel.idCount}')")
                                        if (query1.next()) {
                                            iceCreamViewModel.photo = query1.getBytes(1)
                                            iceCreamViewModel.bitmapImageDB =
                                                BitmapFactory.decodeByteArray(
                                                    iceCreamViewModel.photo,
                                                    0,
                                                    iceCreamViewModel.photo!!.size
                                                )

                                        }
                                    }
                                }
                            }
                        }
                        iceCreamViewModel.itemAdapter.add(
                            IceCreamFastAdapter(
                                IceCreamsDataClass(
                                    "  " + iceCreamViewModel.name.toString(),
                                    "  " + iceCreamViewModel.popis.toString(),
                                    "  " + iceCreamViewModel.alergeny.toString(),
                                    "  " + iceCreamViewModel.cena.toString(),
                                    iceCreamViewModel.bitmapImageDB!!
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