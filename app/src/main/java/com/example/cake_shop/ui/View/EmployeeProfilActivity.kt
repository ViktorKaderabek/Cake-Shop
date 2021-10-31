package com.example.cake_shop.ui.View

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cake_shop.R
import com.example.cake_shop.databinding.ActivityEmployeeProfilBinding
import com.example.cake_shop.model.data.EmployeeProfileDataClass
import com.example.cake_shop.model.data.dbConnection.ConnectionHelper
import com.example.cake_shop.ui.ViewModel.EmployeeProfileViewModel
import com.example.cake_shop.ui.adapter.EmployeeProfileFastAdapter
import java.sql.ResultSet
import java.sql.Statement


class EmployeeProfilActivity : AppCompatActivity() {

    private lateinit var employeeProfileActivityBinding: ActivityEmployeeProfilBinding
    private lateinit var employeeProfileViewModel: EmployeeProfileViewModel
    private val connect = ConnectionHelper().getConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeProfileActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee_profil)

        employeeProfileViewModel = ViewModelProvider(
            this,
            defaultViewModelProviderFactory
        ).get(EmployeeProfileViewModel::class.java)

        employeeProfileActivityBinding.recyclerview.layoutManager =
            LinearLayoutManager(this)
        employeeProfileActivityBinding.recyclerview.adapter =
            employeeProfileViewModel.fastAdapter // Nastavuje recyclerview co bude obsahem
        employeeProfileActivityBinding.recyclerview.setHasFixedSize(true)

        employeeProfileActivityBinding.imbtnBack.setOnClickListener {
            setResult(0)
            finish()
        }

        if (connect != null) { //pokud se pripojeni k dbs zdarilo stane se podminka

            var statement: Statement? = null
            try {
                statement = connect!!.createStatement()
                var query1: ResultSet =
                    statement.executeQuery("SELECT COUNT(1) as NumberOfRows FROM EmployeeInfo")
                if (query1.next()) {
                    employeeProfileViewModel.count = query1.getInt(1)
                    for (i in 1..employeeProfileViewModel.count) {
                        employeeProfileViewModel.idCount += 1
                        query1 =
                            statement.executeQuery("select name from EmployeeInfo where id = ('${employeeProfileViewModel.idCount}')")
                        if (query1.next()) {
                            employeeProfileViewModel.name = query1.getString(1)
                            query1 =
                                statement.executeQuery("select position from EmployeeInfo where id = ('${employeeProfileViewModel.idCount}')")
                            if (query1.next()) {
                                employeeProfileViewModel.position = query1.getString(1)
                                query1 =
                                    statement.executeQuery("select description from EmployeeInfo where id = ('${employeeProfileViewModel.idCount}')")
                                if (query1.next()) {
                                    employeeProfileViewModel.description = query1.getString(1)
                                    query1 =
                                        statement.executeQuery("select photoImage from EmployeeInfo where id = ('${employeeProfileViewModel.idCount}')")
                                    if (query1.next()) {
                                        employeeProfileViewModel.photo = query1.getBytes(1)
                                        employeeProfileViewModel.bitmapImageDB =
                                            BitmapFactory.decodeByteArray(
                                                employeeProfileViewModel.photo,
                                                0,
                                                employeeProfileViewModel.photo!!.size
                                            )

                                    }
                                }
                            }
                        }
                        employeeProfileViewModel.itemAdapter.add(
                            EmployeeProfileFastAdapter(
                                EmployeeProfileDataClass(
                                    employeeProfileViewModel.name.toString(),
                                    employeeProfileViewModel.position.toString(),
                                    employeeProfileViewModel.description.toString(),
                                    employeeProfileViewModel.bitmapImageDB!!
                                )
                            )
                        )
                    }
                }

            } catch (e: Exception) {
                Log.e("Error:", e.message.toString())
            }
        }
    }
}