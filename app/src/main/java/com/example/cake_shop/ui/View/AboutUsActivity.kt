package com.example.cake_shop.ui.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityAboutUsBinding
import com.example.cake_shop.model.data.AboutUsDataClass
import com.example.cake_shop.ui.adapter.AboutUsFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AboutUsActivity : AppCompatActivity() {

    private lateinit var aboutUsBinding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutUsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_about_us)

        aboutUsBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }

        val itemAdapter =
            ItemAdapter<AboutUsFastAdapter>()
        val fastAdapter =
            FastAdapter.with(itemAdapter)


        //promenna ktera v sobe uchovava udaje o tom co je v adapteru

        aboutUsBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        aboutUsBinding.recyclerview.adapter =
            fastAdapter // Nastavuje recyclerview co bude obsahem
        aboutUsBinding.recyclerview.setHasFixedSize(true)

        itemAdapter.add(
            AboutUsFastAdapter(
                AboutUsDataClass(
                    "Who are we ?",
                    "We are a society that welcomes new and talented people, people who are not afraid to embrace a moment of their free time to learn new things, just like us. I'm a prestigious candy factory. We have won many competitions in baking and cake decorating. We certainly have something to offer to our future employees and especially to our customers, whom we welcome with open arms!",
                    R.drawable.cukrarna
                )
            )
        )

        itemAdapter.add(
            AboutUsFastAdapter(
                AboutUsDataClass(
                    "History !",
                    "Here is a little of our history. The company Cake_Shop was founded in 1948 by some ranch guy who probably knew how to bake and therefore decided to make his own company and call it Cake_Shop. This guy used to enter competitions and win them regularly, so he was probably really good. Well, that's a little bit of our Cake Shop history.\n" +
                            "I hope you enjoyed our story.",
                    R.drawable.old_cukrarna
                )
            )
        )

    }
}