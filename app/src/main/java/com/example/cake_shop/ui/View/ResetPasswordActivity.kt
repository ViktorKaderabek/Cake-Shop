package com.example.cake_shop.ui.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityResetPasswordBinding
import com.vishnusivadas.advanced_httpurlconnection.PutData

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityResetPasswordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_reset_password)

        val loginIntent : Intent =
            Intent(this,LogginActivity::class.java)

        binding.imbtnBack.setOnClickListener{
            startActivity(loginIntent)
            finish()
        }

        binding.btnResetPwd.setOnClickListener{

            if (binding.edtxEmail.text.toString()
                    .isNotEmpty() && binding.edtxPassword.text.toString()
                    .isNotEmpty()
            ) {
                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {

                    val field = arrayOfNulls<String>(2)
                    field[0] = "password"
                    field[1] = "email"
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = binding.edtxPassword.text.toString()
                    data[1] = binding.edtxEmail.text.toString()

                    val putData = PutData(
                        "http://192.168.0.242/LoginRegister/resetPassword.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result

                            Log.i("PutData", result)
                            if (result == "Your password has been updated") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(loginIntent)
                                finish()
                            } else {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } //End Write and Read data with URL
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "All fields are requiredsdds",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}