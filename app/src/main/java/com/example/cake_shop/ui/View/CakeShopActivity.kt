package com.example.cake_shop.ui.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var cakeShopActivityBinding: ActivityCakeShopBinding

    private val homeFragment = HomeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cakeShopActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_cake_shop)



        makeCurrentFragment(homeFragment)

        cakeShopActivityBinding.bottomNav.setOnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.ic_home ->
                    if (fragmentManager?.findFragmentById(R.id.ic_home) != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction().apply {
                            show(HomeFragment())
                            commit()
                        }
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction().apply {
                            add(R.id.fl_wrapper, HomeFragment())
                            commit()
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_aboutUs) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(AboutUsFramgen())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_info) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(InfoFragment())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_account) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(AccountFragment())
                                commit()
                            }
                        }
                    }
                R.id.ic_aboutUs ->
                    if (fragmentManager?.findFragmentById(R.id.ic_aboutUs) != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction().apply {
                            show(AboutUsFramgen())
                            commit()
                        }
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction().apply {
                            add(R.id.fl_wrapper, AboutUsFramgen())
                            commit()
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_home) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(HomeFragment())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_info) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(InfoFragment())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_account) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(AccountFragment())
                                commit()
                            }
                        }
                    }
                R.id.ic_info ->
                    if (fragmentManager?.findFragmentById(R.id.ic_info) != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction().apply {
                            show(InfoFragment())
                            commit()
                        }
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction().apply {
                            add(R.id.fl_wrapper, InfoFragment())
                            commit()
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_aboutUs) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(AboutUsFramgen())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_home) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(HomeFragment())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_account) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(AccountFragment())
                                commit()
                            }
                        }
                    }
                R.id.ic_account ->
                    if (fragmentManager?.findFragmentById(R.id.ic_account) != null) {
                        //if the fragment exists, show it.
                        supportFragmentManager.beginTransaction().apply {
                            show(AccountFragment())
                            commit()
                        }
                    } else {
                        //if the fragment does not exist, add it to fragment manager.
                        supportFragmentManager.beginTransaction().apply {
                            add(R.id.fl_wrapper, AccountFragment())
                            commit()
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_aboutUs) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(AboutUsFramgen())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_info) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(InfoFragment())
                                commit()
                            }
                        }
                        if (fragmentManager?.findFragmentById(R.id.ic_home) != null) {
                            //if the other fragment is visible, hide it.
                            supportFragmentManager.beginTransaction().apply {
                                hide(HomeFragment())
                                commit()
                            }
                        }
                    }
            }
            true

        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


}