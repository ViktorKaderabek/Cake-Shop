package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivitySignupBinding
import com.example.cake_shop.ui.ViewModel.SignUpViewModel
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpViwModel: SignUpViewModel
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val mainActivityIntent: Intent =
            Intent(this, MainActivity::class.java)
        val logginIntent: Intent =
            Intent(this, LogginActivity::class.java)

        binding.imbtnBack.setOnClickListener {
            setResult(0, mainActivityIntent)
            finish()
        }
        binding.btnAlreadyAcc.setOnClickListener {
            startActivity(logginIntent)
            finish()
        }

        binding.btnSignup.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                if (binding.edtxFullname.text.toString()
                        .isNotEmpty() && binding.edtxEmail.text.toString()
                        .isNotEmpty() && binding.edtxPassword.text.toString()
                        .isNotEmpty() && binding.edtxUserName.text.toString()
                        .isNotEmpty()
                ) {
                    val handler = Handler(Looper.getMainLooper())
                    handler.post(Runnable {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        val field = arrayOfNulls<String>(4)
                        field[0] = "fullname"
                        field[1] = "username"
                        field[2] = "password"
                        field[3] = "email"
                        //Creating array for data
                        val data = arrayOfNulls<String>(4)
                        data[0] = binding.edtxFullname.text.toString()
                        data[1] = binding.edtxUserName.text.toString()
                        data[2] = binding.edtxPassword.text.toString()
                        data[3] = binding.edtxEmail.text.toString()

                        val putData = PutData(
                            "http://192.168.0.242/LoginRegister/signup.php",
                            "POST",
                            field,
                            data
                        )
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                val result = putData.result
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result)
                                if (result == "Sign Up Success") {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                        .show()
                                    startActivity(logginIntent)
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
                        "All fields are required",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
