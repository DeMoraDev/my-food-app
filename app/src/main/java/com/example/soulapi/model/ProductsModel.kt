package com.example.soulapi.model

import androidx.compose.ui.graphics.ImageBitmap


data class ProductsModel (
    val id: Int,
    val nombre_en: String ,
    val nombre_es: String ,
    val price: Double ,
    val image: String ,
    val ingredients_en: List<String>?,  //Hay productos que no tienen ingredientes ni alergenos
    val ingredients_es: List<String>?,
    val alergenos_en: List<String>?,
    val alergenos_es: List<String>?,
    val tipo: String?,
)