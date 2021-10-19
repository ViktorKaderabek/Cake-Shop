package com.example.cake_shop.ui.View.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentHomeBinding
import com.example.cake_shop.model.HomeDataClass
import com.example.cake_shop.ui.adapter.HomeFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        val view : View = homeFragmentBinding.root

        return view

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        homeFragmentBinding.recyclerview.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            val itemAdapter =
                ItemAdapter<HomeFastAdapter>()

            val fastAdapter =
                FastAdapter.with(itemAdapter) //promenna ktera v sobe uchovava udaje o tom co je v adapteru

            homeFragmentBinding.recyclerview.layoutManager =
                LinearLayoutManager(activity)
            homeFragmentBinding.recyclerview.adapter =
                fastAdapter // Nastavuje recyclerview co bude obsahem
            homeFragmentBinding.recyclerview.setHasFixedSize(true)


                itemAdapter.add(
                    HomeFastAdapter(
                        HomeDataClass(
                            "AHoj",
                            "jak je ?",
                            R.drawable.cake_shop
                        )
                    )
                )



        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)




    }
}