package com.example.soulapi.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.SharedPrefsManager.loadFavorites
import com.example.soulapi.SharedPrefsManager.saveFavorites
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.model.SoulModel
import com.example.soulapi.repository.SoulRepository
import com.example.soulapi.state.BurgerState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    var state by mutableStateOf(BurgerState())
        private set

    private val _favProdcuts = MutableStateFlow<List<Int>>(emptyList())
    val favProducts = _favProdcuts.asStateFlow()

    val cartProducts = listOf<Pair<Int, Int>>()

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

