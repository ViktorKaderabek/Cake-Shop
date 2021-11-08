package com.example.cake_shop.ui.View.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cake_shop.R
import com.example.cake_shop.databinding.FragmentNewsBinding
import com.example.cake_shop.ui.ViewModel.NewsViewMOdel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding //itemy z layoutu
    private lateinit var newsViewModel: NewsViewMOdel // promenna ktera ma odkaz na viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )
        val view = binding.root

        return view //vraci itemy z layoutu
    }


}