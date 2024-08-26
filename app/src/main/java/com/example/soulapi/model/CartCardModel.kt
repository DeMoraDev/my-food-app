package com.example.soulapi.model

data class CartCardModel (
    val productName: String,
    val productPrice: String,
    val productImage: Int,
    val initialQuantity: Int,
    val onQuantityChanged: (Int) -> Unit,
    val onRemoveItem: () -> Unit
)