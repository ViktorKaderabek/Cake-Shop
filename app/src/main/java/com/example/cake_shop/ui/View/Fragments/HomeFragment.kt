package com.example.cake_shop.ui.View.Fragments

import android.content.Context
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
import com.example.cake_shop.ui.adapter.homeFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = DataBindingUtil.inflate(
            inflater, com.example.cake_shop.R.layout.fragment_home, container, false
        )
        val itemAdapter =
            ItemAdapter<homeFastAdapter>()

        val fastAdapter =
            FastAdapter.with(itemAdapter)
        homeFragmentBinding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeFragmentBinding.homeRecyclerView.adapter = fastAdapter
        homeFragmentBinding.homeRecyclerView.setHasFixedSize(true)

        homeFragmentBinding.homeRecyclerView.setOnClickListener {

        itemAdapter.add(
            homeFastAdapter(
                HomeDataClass(
                    title = "Ahoj",
                    secondaryTitle = "jake je ?",
                    image = R.drawable.ic_baseline_arrow_back_ios_24
                )
            )
        )

        fastAdapter.notifyDataSetChanged()
        }
        return view
    }
}