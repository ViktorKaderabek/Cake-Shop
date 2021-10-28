package com.example.cake_shop.ui.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityEmployeeBinding
import com.example.cake_shop.model.data.EmployeeTextDataClass
import com.example.cake_shop.ui.adapter.EmployeeFastAdapter
import com.example.cake_shop.ui.adapter.HomeFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class EmployeeActivity : AppCompatActivity() {

    private lateinit var emplyeeActivityBinding: ActivityEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emplyeeActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee)

        val cakeShopIntent =
            Intent(this, CakeShopActivity::class.java)

        val itemAdapter =
            ItemAdapter<EmployeeFastAdapter>()

        val fastAdapter =
            FastAdapter.with(itemAdapter)


       //promenna ktera v sobe uchovava udaje o tom co je v adapteru

        emplyeeActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        emplyeeActivityBinding.recyclerview.adapter =
            fastAdapter // Nastavuje recyclerview co bude obsahem
        emplyeeActivityBinding.recyclerview.setHasFixedSize(true)

        itemAdapter.add(
            EmployeeFastAdapter(
                EmployeeTextDataClass(
                    " Why Us ?",
                    " Why us?\n There is a simple answer to this question.\n With us you will find the best and most professional team with friendly relationships and a willingness to help everyone without exception.\n We also offer professional growth and progressively reach higher positions."
                )
            )
        )

        itemAdapter.add(
            EmployeeFastAdapter(
                EmployeeTextDataClass(
                    " Our Benefits",
                    " Opportunity for professional growth\n\n 4 Weeks holidaySalary\n\n Around 24 000 czk per month\n\n Company vehicle, available at any time"
                )
            )
        )

        itemAdapter.add(
            EmployeeFastAdapter(
                EmployeeTextDataClass(
                    " Team",
                    "Our whole Team is mad by proffesionals, so don't worry if you wont know something our team will help you imidiatly \n if you want to check our team just click at the buttom bellow\n\n CLICK HERE !!"
                )
            )
        )

        emplyeeActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }
    }
}