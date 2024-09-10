package com.example.soulapi.viewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.savedLists
import com.example.soulapi.SharedPrefsManager.loadFavorites
import com.example.soulapi.SharedPrefsManager.saveFavorites
import com.example.soulapi.model.CartModel
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.repository.SoulRepository
import com.example.soulapi.state.BurgerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SoulViewModel @Inject constructor(
    private val repository: SoulRepository,
    application: Application
) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = application.applicationContext


    // Valores para los productos
    private val _products = MutableStateFlow<List<ProductsModel>>(emptyList())
    val products = _products.asStateFlow()


    // StateFlow para manejar errores
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    //Valores para el CartList

    private val _cartList = MutableStateFlow(savedLists.carlistObversable.value.toList())
    val cartList: StateFlow<List<CartModel>> = _cartList


    fun addList(product: ProductsModel) {
        val existingItem = savedLists.cartList.find { it.product.id == product.id }
        if (existingItem != null) {
            // Actualiza la cantidad del producto existente
            existingItem.quantity.value += 1

            // Fuerza la emisión de un nuevo valor al clonar la lista
            savedLists.carlistObversable.value = savedLists.cartList.toList()
        } else {
            val newItem = CartModel(product, MutableStateFlow(1))
            savedLists.cartList.add(newItem)

            // Emite un nuevo valor con la lista actualizada
            savedLists.carlistObversable.value = savedLists.cartList.toList()
        }
    }
    var state by mutableStateOf(BurgerState())
        private set

    private val _favProdcuts = MutableStateFlow<List<Int>>(emptyList())
    val favProducts = _favProdcuts.asStateFlow()


    fun addFavoriteProduct(productId: Int) {
        val currentFavorites = _favProdcuts.value.toMutableList()

        if (productId !in currentFavorites) {
            currentFavorites.add(productId)
        } else {
            currentFavorites.remove(productId)
        }
        _favProdcuts.value = currentFavorites
        saveFavorites(context, currentFavorites)

    }

    init {
        fetchProducts()
        val currentFavorites = _favProdcuts.value.toMutableList()

        currentFavorites.addAll(loadFavorites(context = context))

        _favProdcuts.value = currentFavorites

    }

    var selectedItemIndex by mutableStateOf(0)

    fun onNavigationItemSelected(index: Int) {
        selectedItemIndex = index
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = withContext(Dispatchers.IO) {
                    repository.getProducts()
                }
                _products.value = products

            } catch (e: Exception) {
                _error.value = "Error al obtener los productos: ${e.message}"
            }
        }
    }
}