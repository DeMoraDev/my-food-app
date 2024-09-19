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

    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total

    private val _delivery = MutableStateFlow(2.0)
    val delivery: StateFlow<Double> = _delivery

    private val _discount = MutableStateFlow(0.0)
    val discount: StateFlow<Double> = _discount

    private val _totalPayment = MutableStateFlow(0.0)
    val totalPayment: StateFlow<Double> = _totalPayment

    init {
        calculateTotals()
    }

    fun onRemoveClick(item: CartModel) {
        savedLists.cartList.remove(item)
        updateCartList()
    }

    fun onIncrementClick(item: CartModel) {
        item.quantity.update { it + 1 }
        calculateTotals()
    }

    fun onDecrementClick(item: CartModel) {
        if (item.quantity.value > 1) {
            item.quantity.update { it - 1 }
        } else {
            onRemoveClick(item)
        }
        calculateTotals()
    }

    private fun updateCartList() {
        savedLists.carlistObversable.value = savedLists.cartList.toList()
        _cartList.value = savedLists.carlistObversable.value.toList()
        calculateTotals()
    }

    private fun calculateTotals() {
        val newTotal = _cartList.value.sumOf { it.product.price * it.quantity.value }
        _total.value = newTotal
        _discount.value = if (newTotal >= 25) 2.0 else 0.0
        _totalPayment.value = newTotal + _delivery.value - _discount.value
    }
}
