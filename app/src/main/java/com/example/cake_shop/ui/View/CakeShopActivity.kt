package com.example.cake_shop.ui.View

import AccountFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityCakeShopBinding
import com.example.cake_shop.ui.View.fragments.AboutUsFramgen
import com.example.cake_shop.ui.View.fragments.ContactsFragment
import com.example.cake_shop.ui.View.fragments.HomeFragment

@Suppress("DEPRECATION")
class CakeShopActivity : AppCompatActivity() {

    private lateinit var cakeShopActivityBinding: ActivityCakeShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cakeShopActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_cake_shop)



        supportFragmentManager.beginTransaction()
            .add(R.id.fl_wrapper, HomeFragment(), "HomeTag").commit()

        cakeShopActivityBinding.bottomNav.setOnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.ic_home -> {
                    if (supportFragmentManager.findFragmentByTag("HomeTag") != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction()
                            .show(supportFragmentManager.findFragmentByTag("HomeTag")!!).commit()

                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fl_wrapper, HomeFragment(), "HomeTag").commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("InfoTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("InfoTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("AboutUsTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("AboutUsTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("AccountTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("AccountTag")!!).commit()
                    }
                }

                R.id.ic_aboutUs -> {
                    if (supportFragmentManager.findFragmentByTag("AboutUsTag") != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction()
                            .show(supportFragmentManager.findFragmentByTag("AboutUsTag")!!).commit()

                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fl_wrapper, AboutUsFramgen(), "AboutUsTag").commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("HomeTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("HomeTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("InfoTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("InfoTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("AccountTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("AccountTag")!!).commit()
                    }
                }

                R.id.ic_info -> {
                    if (supportFragmentManager.findFragmentByTag("InfoTag") != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction()
                            .show(supportFragmentManager.findFragmentByTag("InfoTag")!!).commit()

                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fl_wrapper, ContactsFragment(), "InfoTag").commit()

                    }
                    if (supportFragmentManager.findFragmentByTag("HomeTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("HomeTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("AboutUsTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("AboutUsTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("AccountTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("AccountTag")!!).commit()
                    }
                }

                R.id.ic_account -> {
                    if (supportFragmentManager.findFragmentByTag("AccountTag") != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction()
                            .show(supportFragmentManager.findFragmentByTag("AccountTag")!!).commit()

                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fl_wrapper, AccountFragment(), "AccountTag").commit()

                    }
                    if (supportFragmentManager.findFragmentByTag("HomeTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("HomeTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("AboutUsTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("AboutUsTag")!!).commit()
                    }
                    if (supportFragmentManager.findFragmentByTag("InfoTag") != null) {
                        //if the other fragment is visible, hide it.
                        supportFragmentManager.beginTransaction()
                            .hide(supportFragmentManager.findFragmentByTag("InfoTag")!!).commit()
                    }
                }
            }
            true
        }
    }
}