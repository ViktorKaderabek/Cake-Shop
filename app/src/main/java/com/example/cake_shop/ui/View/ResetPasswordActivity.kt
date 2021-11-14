package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityResetPasswordBinding
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.ResetPasswordViewModel
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel
    private val connect = ConnectionHelper().getConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_reset_password)

        resetPasswordViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(ResetPasswordViewModel::class.java)

        val loginIntent: Intent =
            Intent(this, LogginActivity::class.java)

        binding.imbtnBack.setOnClickListener {
            startActivity(loginIntent)
            finish()
        }

        binding.btnResetPwd.setOnClickListener {

            if (binding.edtxEmail.text.isNotEmpty() &&//pokud jsou vsechna pole zaplnena tak se provede podminka
                binding.edtxPassword.text.isNotEmpty()

            ) {
                if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka

                    resetPasswordViewModel.email = binding.edtxEmail.text.toString()
                    resetPasswordViewModel.password = binding.edtxPassword.text.toString()

                    var statement: Statement? = null
                    try {

                        statement = connect!!.createStatement()

                        var resultSet: ResultSet =
                            statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Users where email = ('${resetPasswordViewModel.email}');")//secte kolik je shod s danou email.adress
                        var result: String = ""

                        if (resultSet.next()) {
                            result = resultSet.getString(1)

                            if (result == "1") {
                                startActivity(loginIntent)
                                resultSet =
                                    statement.executeQuery("Update Users Set password = ('${resetPasswordViewModel.password}') where email = ('${resetPasswordViewModel.email}');")
                                Toast.makeText(
                                    applicationContext,
                                    "Password change successfuly",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else if (result == "0") {
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
            }
            else
            {
                Toast.makeText(applicationContext,
                               "All fields are required",
                               Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}