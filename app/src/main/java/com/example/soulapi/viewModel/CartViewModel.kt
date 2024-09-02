package com.example.soulapi.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.soulapi.model.CartModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    // Lista mutable de cart models
    private val _cartList = MutableStateFlow<List<CartModel>>(emptyList())
    val cartList: StateFlow<List<CartModel>> get() = _cartList

    // Agregar un producto al carrito
    fun addProduct(product: CartModel) {
        _cartList.value = _cartList.value + product
    }

    // Eliminar un producto del carrito
    fun onRemoveClick(item: CartModel) {
        _cartList.value = _cartList.value.filter { it != item }
    }

    // Disminuir la cantidad de un producto
    fun onDecrementClick(item: CartModel) {
        _cartList.value = _cartList.value.map {
            if (it == item && it.quantity > 1) {
                it.copy(quantity = it.quantity - 1)
            } else it
        }
    }

    // Aumentar la cantidad de un producto
    fun onIncrementClick(item: CartModel) {
        _cartList.value = _cartList.value.map {
            if (it == item) {
                it.copy(quantity = it.quantity + 1)
            } else it
        }
    }
}


