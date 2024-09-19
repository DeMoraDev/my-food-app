package com.example.soulapi.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.model.CartModel
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.savedLists
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _cartList = MutableStateFlow(savedLists.carlistObversable.value.toList())
    val cartList: StateFlow<List<CartModel>> = _cartList

    fun onRemoveClick(item: CartModel) {
        // Agregar el nuevo ítem a la lista mutable
        savedLists.cartList.remove(item)
        // Actualizar el MutableStateFlow con una nueva lista
        savedLists.carlistObversable.value = savedLists.cartList.toList()
        _cartList.value = savedLists.carlistObversable.value.toList()
    }

    fun onIncrementClick(item: CartModel) {
        item.quantity.update { it + 1 }
    }

    fun onDecrementClick(item: CartModel) {
        if (item.quantity.value > 1) {
            item.quantity.update { it - 1 }
        } else {
            onRemoveClick(item)
        }
    }

    fun getDiscount(): Double {
        val total = _cartList.value.sumOf { it.product.price * it.quantity.value }
        return if (total >= 25) {
            2.0 //Si la compra supera los 25, el descuento será de 2€, envío gratis
        } else {
            0.0
        }
    }
}