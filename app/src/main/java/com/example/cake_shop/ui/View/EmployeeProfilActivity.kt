package com.example.cake_shop.ui.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityEmployeeProfilBinding
import com.example.cake_shop.model.data.EmployeeTextDataClass
import com.example.cake_shop.ui.adapter.EmployeeFastAdapter
import com.example.cake_shop.ui.adapter.EmployeeProfileFastAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class EmployeeProfilActivity : AppCompatActivity() {

    private lateinit var employeeProfileActivityBinding : ActivityEmployeeProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeProfileActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee_profil)


        val itemAdapter =
            ItemAdapter<EmployeeProfileFastAdapter>()
        val fastAdapter =
            FastAdapter.with(itemAdapter)

        employeeProfileActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        employeeProfileActivityBinding.recyclerview.adapter =
            fastAdapter // Nastavuje recyclerview co bude obsahem
        employeeProfileActivityBinding.recyclerview.setHasFixedSize(true)

        employeeProfileActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }


    }
}