package com.example.soulapi

import com.example.soulapi.model.CartModel
import kotlinx.coroutines.flow.MutableStateFlow

object savedLists {
    val cartList = mutableListOf<CartModel>()
    val carlistObversable = MutableStateFlow<List<CartModel>>(emptyList())
}