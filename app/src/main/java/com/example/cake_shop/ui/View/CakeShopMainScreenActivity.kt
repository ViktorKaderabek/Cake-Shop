package com.example.cake_shop.ui.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityCakeShopMainScreenBinding
import com.example.cake_shop.ui.View.Fragments.AboutUsFragment
import com.example.cake_shop.ui.View.Fragments.AccountFragment
import com.example.cake_shop.ui.View.Fragments.HomeFragment
import com.example.cake_shop.ui.View.Fragments.ProductsFragment
@Suppress("DEPRECATION")
class CakeShopMainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCakeShopMainScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_cake_shop_main_screen)

        val homeFragmet = HomeFragment()
        val productsFragment = ProductsFragment()
        val aboutUsFragment = AboutUsFragment()
        val accountFragment = AccountFragment()

        makeCurrentFragment(homeFragmet)

        binding.bottomNavgation.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.ic_Home -> makeCurrentFragment(homeFragmet)
                R.id.ic_Products -> makeCurrentFragment(productsFragment)
                R.id.ic_AboutUs -> makeCurrentFragment(aboutUsFragment)
                R.id.ic_account -> makeCurrentFragment(accountFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            commit()

        }


}