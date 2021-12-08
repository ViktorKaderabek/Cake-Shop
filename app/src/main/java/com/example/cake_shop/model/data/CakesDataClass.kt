package com.example.cake_shop.model.data

import android.graphics.Bitmap

data class CakesDataClass(
//data pro recyclerView
    val name: String,
    val popis: String,
    val alergeny: String,
    val cena: String,
    val picture: Bitmap,
)
