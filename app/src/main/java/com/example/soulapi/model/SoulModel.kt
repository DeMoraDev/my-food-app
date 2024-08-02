package com.example.soulapi.model

import com.google.gson.annotations.SerializedName

data class SoulModel(
    val id: Int,
    val name: String ,
    val price: Double ,
    val image: String ,
    val ingredients: List<String>,
    @SerializedName("additional_info")
    val additionalInfo: String
)
