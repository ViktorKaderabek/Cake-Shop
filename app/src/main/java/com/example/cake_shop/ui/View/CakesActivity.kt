package com.example.cake_shop.ui.View

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityCakesBinding
import com.example.cake_shop.model.data.CakesDataClass
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.CakesActivityViewModel
import com.example.cake_shop.ui.adapter.CakesFastAdapter
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class CakesActivity : AppCompatActivity() {

    private lateinit var cakeViewModel: CakesActivityViewModel
    private lateinit var cakesActivityBinding: ActivityCakesBinding
    private val connect = ConnectionHelper().getConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cakesActivityBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_cakes
            )

        cakeViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(CakesActivityViewModel::class.java)

        cakesActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)

        cakesActivityBinding.recyclerview.adapter =
            cakeViewModel.fastAdapter
        // Nastavuje recyclerview co bude obsahem
        cakesActivityBinding.recyclerview.setHasFixedSize(true)

        cakesActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }

        if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka
            Log.e(
                "message:",
                "connection was succes"
            )
            var statement: Statement? = null
            try {
                statement = connect!!.createStatement()
                var query1: ResultSet =
                    statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Cakes")
                if (query1.next()) {
                    cakeViewModel.count = query1.getInt(1)
                    for (i in 1..cakeViewModel.count) {
                        cakeViewModel.idCount += 1
                        query1 =
                            statement.executeQuery("select nazev from Cakes where id = ('${cakeViewModel.idCount}')")
                        if (query1.next()) {
                            cakeViewModel.name = query1.getString(1)
                            query1 =
                                statement.executeQuery("select popis from Cakes where id = ('${cakeViewModel.idCount}')")
                            if (query1.next()) {
                                cakeViewModel.popis = query1.getString(1)
                                query1 =
                                    statement.executeQuery("select alergeny from Cakes where id = ('${cakeViewModel.idCount}')")
                                if (query1.next()) {
                                    cakeViewModel.alergeny = query1.getString(1)
                                    query1 =
                                        statement.executeQuery("select cena from Cakes where id = ('${cakeViewModel.idCount}')")
                                    if (query1.next()) {
                                        cakeViewModel.cena = query1.getString(1)
                                        query1 =
                                            statement.executeQuery("select photo from Cakes where id = ('${cakeViewModel.idCount}')")
                                        if (query1.next()) {
                                            cakeViewModel.photo = query1.getBytes(1)
                                            cakeViewModel.bitmapImageDB =
                                                BitmapFactory.decodeByteArray(
                                                    cakeViewModel.photo,
                                                    0,
                                                    cakeViewModel.photo!!.size
                                                )

                                        }
                                    }
                                }
                            }
                        }
                        cakeViewModel.itemAdapter.add(
                            CakesFastAdapter(
                                CakesDataClass(
                                    "  " + cakeViewModel.name.toString(),
                                    "  " + cakeViewModel.popis.toString(),
                                    "  " + cakeViewModel.alergeny.toString(),
                                    "  " + cakeViewModel.cena.toString(),
                                    cakeViewModel.bitmapImageDB!!
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
