package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityLogginBinding
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.LoginViewModel
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class LogginActivity : AppCompatActivity() {

    private lateinit var logginViewModel: LoginViewModel //Vytvarim promennou, ktera odkazuje na ViewModel pro tuto tridu
    private lateinit var logginBinding: ActivityLogginBinding
    private val connectionHelper = ConnectionHelper()
    private val connect = connectionHelper.getConnection()
    //Vytvarim promennou, ktera odkazuje na fragment com.example.cake_shop.ui.View.fragments.AccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_loggin)

        logginViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory).get(LoginViewModel::class.java)

        val mainActivityIntent: Intent =
            Intent(this, MainActivity::class.java)
        val resetPasswordIntent: Intent =
            Intent(this, ResetPasswordActivity::class.java)
        val cakeShopIntent: Intent =
            Intent(this, CakeShopActivity::class.java)


        logginBinding.imbtnBack.setOnClickListener {
            setResult(0, mainActivityIntent)
            finish()
        }

        logginBinding.btnResetPwd.setOnClickListener {
            startActivity(resetPasswordIntent)
            finish()
        }

        logginBinding.btnLogin.setOnClickListener {

            if (logginBinding.edtxEmail.text.isNotEmpty() &&//pokud jsou vsechna pole zaplnena tak se provede podminka
                logginBinding.edtxPassword.text.isNotEmpty()

            ) {
                if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka

                    logginViewModel.email = logginBinding.edtxEmail.text.toString()
                    logginViewModel.password = logginBinding.edtxPassword.text.toString()

                    var statement: Statement? = null
                    try {

                        statement = connect!!.createStatement()

                        var query1: ResultSet =
                            statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Users where email = ('${logginViewModel.email}')")
                        var result1: String = ""


                        if (query1.next()) {
                            result1 = query1.getString(1)

                            if (result1 == "1") {
                                startActivity(cakeShopIntent)
                                query1 =
                                    statement.executeQuery("Update EmailHolder Set email = ('${logginViewModel.email}'),state = ('true') where id = ('1');")
                                Toast.makeText(
                                    applicationContext,
                                    "Successful Log In",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else if (result1 == "0") {
                                query1 =
                                    statement.executeQuery("Update EmailHolder Set state = ('false') where id = ('1');")
                                Toast.makeText(
                                    applicationContext,
                                    "Incorrect email or password",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }

                    } catch (e: SQLException) {
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(applicationContext, "Connection is null", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}