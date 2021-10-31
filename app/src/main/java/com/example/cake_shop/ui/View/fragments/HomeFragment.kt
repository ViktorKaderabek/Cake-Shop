package com.example.cake_shop.ui.View.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentHomeBinding
import com.example.cake_shop.model.data.HomeDataClass
import com.example.cake_shop.ui.View.AboutUsActivity
import com.example.cake_shop.ui.View.EmployeeActivity
import com.example.cake_shop.ui.View.ProductsActivity
import com.example.cake_shop.ui.adapter.HomeFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.select.getSelectExtension

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        val view: View = homeFragmentBinding.root

        return view

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        homeFragmentBinding.recyclerview.apply {

            val itemAdapter =
                ItemAdapter<HomeFastAdapter>()
            val fastAdapter =
                FastAdapter.with(itemAdapter) //promenna ktera v sobe uchovava udaje o tom co je v adapteru

            val employeeActivityIntent =
                Intent(activity, EmployeeActivity::class.java)
            val aboutUsIntent =
                Intent(activity, AboutUsActivity::class.java)
            val productsIntent =
                Intent(activity, ProductsActivity::class.java)

            homeFragmentBinding.recyclerview.layoutManager =
                LinearLayoutManager(activity)
            homeFragmentBinding.recyclerview.adapter =
                fastAdapter // Nastavuje recyclerview co bude obsahem
            homeFragmentBinding.recyclerview.setHasFixedSize(true)

            val selectExtension = fastAdapter.getSelectExtension()
            selectExtension.isSelectable = true
            selectExtension.multiSelect = true
            selectExtension.selectOnLongClick = false

            itemAdapter.add(
                HomeFastAdapter(
                    HomeDataClass(
                        "  Home",
                        "  Let's Find Out Who We Are !!",
                        R.drawable.cake_shop,
                        R.drawable.ic_baseline_home_24
                    )
                )
            )

            itemAdapter.add(
                HomeFastAdapter(
                    HomeDataClass(
                        "  Products",
                        "  Enjoy Our Products Here Right Now !!",
                        R.drawable.cake_shop_products,
                        R.drawable.product
                    )
                )
            )

            itemAdapter.add(
                HomeFastAdapter(
                    HomeDataClass(
                        "  Our Employees",
                        "  Become Our Employee And Find YourSelf Here !!",
                        R.drawable.happy_employee,
                        R.drawable.employee
                    )
                )
            )

            fastAdapter.onClickListener = { view, adapter, item, position ->

                if (position == 0) {
                    startActivityForResult(aboutUsIntent, 1)
                } else if (position == 1) {
                    startActivityForResult(productsIntent, 1)
                } else if (position == 2) {
                    startActivityForResult(employeeActivityIntent, 1)
                }
                false
            }

        }
    }
}