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

    private val _cartList = MutableStateFlow(savedLists.cartList.toList())
    val cartList: StateFlow<List<CartModel>> = _cartList

    fun onRemoveClick(item: CartModel) {
        savedLists.cartList.remove(item)
        _cartList.value = savedLists.cartList.toList() // Actualizar el StateFlow con la lista actualizada
    }

    fun onIncrementClick(item: CartModel) {
        item.quantity.update { it + 1 } // Actualizar el valor de quantity dentro de StateFlow
    }

    fun onDecrementClick(item: CartModel) {
        if (item.quantity.value > 1) {  // Chequea el valor actual de StateFlow
            item.quantity.update { it - 1 } // Decrementa el valor de quantity
        } else {
            onRemoveClick(item)
        }
    }
}