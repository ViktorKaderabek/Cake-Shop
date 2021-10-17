package com.example.cake_shop.ui.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityHomeBinding
import com.example.cake_shop.model.HomeDataClass
import com.example.cake_shop.ui.ViewModel.HomeViewModel
import com.example.cake_shop.ui.adapter.HomeFastAdapter
import com.mikepenz.fastadapter.FastAdapter

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)

        activityHomeBinding.navHome.setBackgroundColor(Color.parseColor("#E3D312"))
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val accountActivityIntent: Intent =
            Intent(this, AccountActivity::class.java)
        val aboutUsActivityIntent: Intent =
            Intent(this, AboutUsActivity::class.java)
        val infoActivityIntent: Intent =
            Intent(this, InfoActivity::class.java)

        val fastAdapter =
            FastAdapter.with(homeViewModel.itemAdapter) //promenna ktera v sobe uchovava udaje o tom co je v adapteru

        activityHomeBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        activityHomeBinding.recyclerview.adapter =
            fastAdapter // Nastavuje recyclerview co bude obsahem
        activityHomeBinding.recyclerview.setHasFixedSize(true)

        for (i in 1..10) {
            homeViewModel.itemAdapter.add(
                HomeFastAdapter(
                    HomeDataClass(
                        "AHoj",
                        "jak je ?",
                        R.drawable.ic_baseline_arrow_back_ios_24
                    )
                )
            )
        }

        activityHomeBinding.navAboutUs.setOnClickListener {
            startActivityForResult(aboutUsActivityIntent, 1)
        }
        activityHomeBinding.navAccount.setOnClickListener {
            startActivityForResult(accountActivityIntent, 1)
        }

        activityHomeBinding.navInfo.setOnClickListener {
            startActivityForResult(infoActivityIntent, 1)
        }
    }
}