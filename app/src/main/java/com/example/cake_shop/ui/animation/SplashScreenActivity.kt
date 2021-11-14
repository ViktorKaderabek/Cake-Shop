package com.example.cake_shop.ui.animation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivitySplashScreenBinding
import com.example.cake_shop.ui.View.MainActivity

class SplashScreenActivity : AppCompatActivity()
{
     
     private lateinit var binding : ActivitySplashScreenBinding //binding itemu z layoutu
     
     override fun onCreate(savedInstanceState : Bundle?)
     {
	super.onCreate(savedInstanceState)
	
	binding =
	     DataBindingUtil.setContentView(this,
				      R.layout.activity_splash_screen)
	
	binding.ivLoading.alpha = 0f
	binding.ivLoading.animate()
	    .setDuration(5000)
	    .alpha(1f) //animace
	    .withEndAction { //kdyz animace skonci
	         
	         val intent = Intent(
		    this,
		    MainActivity::class.java
			        ) //Activita ktera se zapne az skonci animace
	         
	         overridePendingTransition( //typ animace
		    android.R.anim.fade_in,
		    android.R.anim.fade_out
				    )
	         startActivity(intent) //zapne se Activita ktera je vyse definovana
	         finish()//tahle Activita se znici
	    }
     }
}