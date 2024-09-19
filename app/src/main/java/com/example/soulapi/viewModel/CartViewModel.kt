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

    private val _totalItemCount = MutableStateFlow(0)
    val totalItemCount: StateFlow<Int> = _totalItemCount

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    private val _totalPayment = MutableStateFlow(0.0)
    val totalPayment: StateFlow<Double> = _totalPayment

    private val deliveryFee = 2.00

    init {
        updateCartValues()
    }

    fun onRemoveClick(item: CartModel) {
        savedLists.cartList.remove(item)
        updateCartList()
    }

    fun onIncrementClick(item: CartModel) {
        item.quantity.update { it + 1 }
        updateCartList()
    }

    fun onDecrementClick(item: CartModel) {
        if (item.quantity.value > 1) {
            item.quantity.update { it - 1 }
        } else {
            onRemoveClick(item)
        }
        updateCartList()
    }

    private fun updateCartList() {
        savedLists.carlistObversable.value = savedLists.cartList.toList()
        _cartList.value = savedLists.carlistObversable.value.toList()
        updateCartValues()
    }

    private fun updateCartValues() {
        val newTotalItemCount = _cartList.value.sumOf { it.quantity.value }
        _totalItemCount.value = newTotalItemCount

        val newTotalPrice = _cartList.value.sumOf { it.product.price * it.quantity.value }
        _totalPrice.value = newTotalPrice

        val discount = getDiscount(newTotalPrice)
        _totalPayment.value = newTotalPrice + deliveryFee - discount
    }

    fun getDiscount(total: Double): Double {
        return if (total >= 25) {
            2.0 // Si la compra supera los 25, el descuento será de 2€, envío gratis
        } else {
            0.0
        }
    }
}
