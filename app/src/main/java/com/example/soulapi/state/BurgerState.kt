package com.example.soulapi.state

data class BurgerState(

    val id: Int = 0,
    val name: String = "",
    val price: Double = 0.0,
    val image: String = "",
    val ingredients: List<String> = emptyList(),
    val additionalInfo: String = ""
)
