package com.example.soulapi.model

import com.google.gson.annotations.SerializedName

data class ImagesModel (
    val id: Int,
    @SerializedName("nombre")
    val name: String ,
    @SerializedName("imagen")
    val image: String
)
