package com.example.cake_shop.model.data

import android.graphics.Bitmap

data class CakesDataClass(
    val name: String, //data do recycerlView
    val popis: String,
    val alergeny: String,
    val cena: String,
    val picture: Bitmap
)
