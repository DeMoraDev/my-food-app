package com.example.soulapi.viewModel

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.model.SoulModel
import com.example.soulapi.repository.SoulRepository
import com.example.soulapi.state.BurgerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SoulViewModel @Inject constructor(private val repository: SoulRepository) : ViewModel() {

    // Valores para los productos
    private val _products = MutableStateFlow<List<ProductsModel>>(emptyList())
    val products = _products.asStateFlow()


    // StateFlow para manejar errores
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    var state by mutableStateOf(BurgerState())
        private set

    init {
        fetchProducts()
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

