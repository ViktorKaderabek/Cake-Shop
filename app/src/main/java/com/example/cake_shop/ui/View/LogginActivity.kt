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
import com.example.cake_shop.databinding.ActivityLogginBinding
import com.example.cake_shop.databinding.FragmentAccountBinding
import com.example.cake_shop.ui.View.Fragments.AccountFragment
import com.example.cake_shop.ui.ViewModel.LoginViewModel
import com.vishnusivadas.advanced_httpurlconnection.PutData

class LogginActivity : AppCompatActivity() {

    private lateinit var logginViewModel: LoginViewModel //Vytvarim promennou, ktera odkazuje na ViewModel pro tuto tridu
    private lateinit var logginBinding: ActivityLogginBinding
    private lateinit var accountFragmentBinding : FragmentAccountBinding //Vytvarim promennou, ktera odkazuje na fragment AccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logginBinding=
            DataBindingUtil.setContentView(this, R.layout.activity_loggin)

        val mainActivityIntent: Intent =
            Intent(this, MainActivity::class.java)
        val cakeShopMainActivity: Intent =
            Intent(this, CakeShopMainScreenActivity::class.java)
        val resetPasswordIntent: Intent =
            Intent(this, ResetPasswordActivity::class.java)
        val accountFragmentIntent : Intent =
            Intent(this, AccountFragment::class.java)


        logginBinding.imbtnBack.setOnClickListener {
            setResult(0, mainActivityIntent)
            finish()
        }

        logginBinding.btnResetPwd.setOnClickListener {
            startActivity(resetPasswordIntent)
            finish()
        }


        logginBinding.btnLogin.setOnClickListener {

            val email = logginBinding.edtxEmail.text

            if (logginBinding.edtxEmail.text.toString()
                    .isNotEmpty() && logginBinding.edtxPassword.text.toString()
                    .isNotEmpty()
            ) {
                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {

                    val field = arrayOfNulls<String>(2) //promenna ktera je Array s nazvem field
                    field[0] = "password" //nazev fieldu [0]
                    field[1] = "email" //nazev fieldu [1]
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)//promenna ktera je Array s nazvem data
                    data[0] = logginBinding.edtxPassword.text.toString() //nazev data [0]
                    data[1] = logginBinding.edtxEmail.text.toString()//nazev data [1]

                    val putData = PutData( //promenna s nazvem putData
                        "http://192.168.0.242/LoginRegister/login.php", //vkladam do ni url adresu ktera odkazuje na funkci v php jazyce
                        "POST",
                        field, //tady zadavam do field[0] password z data[0]
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            val result = putData.result // do p
                            Log.i("PutData", result)
                            if (result == "Login Success") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                accountFragmentIntent.putExtra("email",email)
                                startActivity(cakeShopMainActivity)
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