package com.example.cake_shop.ui.View.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cake_shop.R

import android.content.Intent
import android.widget.Toast
import com.example.cake_shop.databinding.FragmentAccountBinding
import com.example.cake_shop.ui.View.MainActivity


class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountBinding = DataBindingUtil.inflate(
            inflater, com.example.cake_shop.R.layout.fragment_account, container, false)
        val view = binding.root

        binding.btnSignOut.setOnClickListener {

            val intent=Intent(activity,MainActivity::class.java)
            startActivity(intent)

            Toast.makeText(activity,"You've been logged out", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}