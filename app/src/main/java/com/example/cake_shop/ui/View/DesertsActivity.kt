package com.example.cake_shop.ui.View

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityDesertsBinding
import com.example.cake_shop.model.data.DessertDataClass
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.DesertsViewModel
import com.example.cake_shop.ui.adapter.DessertFastAdapter
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DesertsActivity : AppCompatActivity() {

    private lateinit var dessertsViewModel: DesertsViewModel
    private lateinit var dessertsActivityBinding: ActivityDesertsBinding
    private val connect = ConnectionHelper().getConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dessertsActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_deserts)

        dessertsViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(DesertsViewModel::class.java)

        dessertsActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        dessertsActivityBinding.recyclerview.adapter =
            dessertsViewModel.fastAdapter // Nastavuje recyclerview co bude obsahem
        dessertsActivityBinding.recyclerview.setHasFixedSize(true)

        dessertsActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }


        if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null
            try {
                statement = connect!!.createStatement()
                var query1: ResultSet =
                    statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM dezerty")
                if (query1.next()) {
                    dessertsViewModel.count = query1.getInt(1)
                    for (i in 1..dessertsViewModel.count) {
                        dessertsViewModel.idCount += 1
                        query1 =
                            statement.executeQuery("select nazev from dezerty where id = ('${dessertsViewModel.idCount}')")
                        if (query1.next()) {
                            dessertsViewModel.name = query1.getString(1)
                            query1 =
                                statement.executeQuery("select popis from dezerty where id = ('${dessertsViewModel.idCount}')")
                            if (query1.next()) {
                                dessertsViewModel.popis = query1.getString(1)
                                query1 =
                                    statement.executeQuery("select alergeny from dezerty where id = ('${dessertsViewModel.idCount}')")
                                if (query1.next()) {
                                    dessertsViewModel.alergeny = query1.getString(1)
                                    query1 =
                                        statement.executeQuery("select cena from dezerty where id = ('${dessertsViewModel.idCount}')")
                                    if (query1.next()) {
                                        dessertsViewModel.cena = query1.getString(1)
                                        query1 =
                                            statement.executeQuery("select photo from dezerty where id = ('${dessertsViewModel.idCount}')")
                                        if (query1.next()) {
                                            dessertsViewModel.photo = query1.getBytes(1)
                                            dessertsViewModel.bitmapImageDB =
                                                BitmapFactory.decodeByteArray(
                                                    dessertsViewModel.photo,
                                                    0,
                                                    dessertsViewModel.photo!!.size
                                                )

                                        }
                                    }
                                }
                            }
                        }
                        dessertsViewModel.itemAdapter.add(
                            DessertFastAdapter(
                                DessertDataClass(
                                    "  " + dessertsViewModel.name.toString(),
                                    "  " + dessertsViewModel.popis.toString(),
                                    "  " + dessertsViewModel.alergeny.toString(),
                                    "  " + dessertsViewModel.cena.toString(),
                                    dessertsViewModel.bitmapImageDB!!
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