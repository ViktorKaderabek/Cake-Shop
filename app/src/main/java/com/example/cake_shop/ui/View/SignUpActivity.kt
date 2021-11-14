package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivitySignupBinding
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.SignUpViewModel
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class SignUpActivity : AppCompatActivity()
{
     
     private lateinit var signUpViwModel : SignUpViewModel
     private lateinit var binding : ActivitySignupBinding
     private val connect = ConnectionHelper().getConnection()
     
     override fun onCreate(savedInstanceState : Bundle?)
     {
	super.onCreate(savedInstanceState)
	binding =
	     DataBindingUtil.setContentView(this,
				      R.layout.activity_signup)
	
	signUpViwModel = ViewModelProvider(
	     this,
	     defaultViewModelProviderFactory
				    ).get(SignUpViewModel::class.java)
	
	val mainActivityIntent : Intent =
	     Intent(this,
		  MainActivity::class.java)
	val logginIntent : Intent =
	     Intent(this,
		  LogginActivity::class.java)
	
	binding.imbtnBack.setOnClickListener {
	     setResult(0,
		     mainActivityIntent)
	     finish()
	}
	
	binding.btnAlreadyAcc.setOnClickListener {
	     startActivity(logginIntent)
	     finish()
	}
	
	binding.btnSignup.setOnClickListener {
	     
	     if (binding.edtxEmail.text.isNotEmpty() &&//pokud jsou vsechna pole zaplnena tak se provede podminka
	         binding.edtxUserName.text.isNotEmpty() &&
	         binding.edtxPassword.text.isNotEmpty() &&
	         binding.edtxFullname.text.isNotEmpty()
	     )
	     {
		if (connect != null)
		{ //pokud se pripojeni k dbs zdarilo stane se podminka
		     
		     signUpViwModel.email = binding.edtxEmail.text.toString()
		     signUpViwModel.username = binding.edtxUserName.text.toString()
		     signUpViwModel.password = binding.edtxPassword.text.toString()
		     signUpViwModel.name = binding.edtxFullname.text.toString()
		     
		     var statement : Statement? = null
		     try
		     {
			
			statement = connect !!.createStatement()
			
			var resultSet : ResultSet =
			     statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM Users where email = ('${signUpViwModel.email}')")//secte kolik je shod s danou email.adress
			var result : String = ""
			
			if (resultSet.next())
			{
			     result = resultSet.getString(1)
			     
			     if (result == "0")
			     {
				startActivity(logginIntent)
				resultSet =
				     statement.executeQuery("Insert into Users(email,username,name,password) VALUES ('${signUpViwModel.email}','${signUpViwModel.username}','${signUpViwModel.name}','${signUpViwModel.password}');")
				Toast.makeText(
				     applicationContext,
				     "Successful Sign Up",
				     Toast.LENGTH_SHORT
					    )
				    .show()
			     }
			     else
			     {
				Toast.makeText(
				     applicationContext,
				     "User Already Exists",
				     Toast.LENGTH_SHORT
					    )
				    .show()
			     }
			}
			
		     }
		     catch (e : SQLException)
		     {
			e.printStackTrace()
		     }
		     
		}
		else
		{
		     Toast.makeText(applicationContext,
				"Connection is null",
				Toast.LENGTH_SHORT)
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
