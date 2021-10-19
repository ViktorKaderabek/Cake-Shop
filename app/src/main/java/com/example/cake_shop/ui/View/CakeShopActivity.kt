package com.example.cake_shop.ui.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityCakeShopBinding
import com.example.cake_shop.ui.View.fragments.AboutUsFramgen
import com.example.cake_shop.ui.View.fragments.AccountFragment
import com.example.cake_shop.ui.View.fragments.HomeFragment
import com.example.cake_shop.ui.View.fragments.InfoFragment
@Suppress("DEPRECATION")
class CakeShopActivity : AppCompatActivity() {

    private lateinit var cakeShopActivityBinding : ActivityCakeShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cakeShopActivityBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_cake_shop)

         val homeFragment = HomeFragment()
         val aboutUsFragment = AboutUsFramgen()
         val inforFragment = InfoFragment()
         val accountFragment = AccountFragment()

        makeCurrentFragment(homeFragment)

        cakeShopActivityBinding.bottomNav.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_aboutUs -> makeCurrentFragment(aboutUsFragment)
                R.id.ic_info -> makeCurrentFragment(inforFragment)
                R.id.ic_account -> makeCurrentFragment(accountFragment)
            }
            true
        }

    }
    private fun makeCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }
}