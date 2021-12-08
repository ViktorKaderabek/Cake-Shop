package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityMainBinding
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.MainViewModel
import java.sql.ResultSet
import java.sql.Statement

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityMainViewModel: MainViewModel
    private val connect = ConnectionHelper().getConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )

        activityMainViewModel =
            ViewModelProvider(
                this,
                defaultViewModelProviderFactory
            ).get(MainViewModel::class.java)

        val loginIntent: Intent =
            Intent(
                this,
                LogginActivity::class.java
            )
        val signUpIntent: Intent =
            Intent(
                this,
                SignUpActivity::class.java
            )
        val cakeShopIntent: Intent =
            Intent(
                this,
                CakeShopActivity::class.java
            )

        binding.btnSignup.setOnClickListener {
            startActivityForResult(
                signUpIntent,
                1
            )
        }

        binding.btnLogin.setOnClickListener {
            startActivityForResult(
                loginIntent,
                1
            )
        }

        if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null
            try {

                statement = connect!!.createStatement()

                var query1: ResultSet =
                    statement.executeQuery("Select state from EmailHolder where id = ('1')")
                var result1: String? = null
                if (query1.next()) {

                    result1 = query1.getString(1)
                    if (result1 == "true") {
                        startActivity(cakeShopIntent)
                    }
                }

            } catch (e: Exception) {
                Log.e(
                    "Error :",
                    e.message.toString()
                )
            }
        }
    }
}