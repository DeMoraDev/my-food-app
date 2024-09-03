package com.example.soulapi.model

import kotlinx.coroutines.flow.MutableStateFlow

data class CartModel (
    val product: ProductsModel,
    val quantity: MutableStateFlow<Int> = MutableStateFlow(1)
)