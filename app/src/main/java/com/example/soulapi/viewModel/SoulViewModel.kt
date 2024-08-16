package com.example.soulapi.viewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.model.BottomNavigationItem
import com.example.soulapi.model.SoulModel
import com.example.soulapi.repository.SoulRepository
import com.example.soulapi.state.BurgerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SoulViewModel @Inject constructor(private val repository: SoulRepository) : ViewModel() {

    private val _burgers = MutableStateFlow<List<SoulModel>>(emptyList())
    val burgers = _burgers.asStateFlow()

    // StateFlow para manejar errores
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    var state by mutableStateOf(BurgerState())
        private set

    init {
        fetchBurgers()
    }

    fun fetchBurgers() {
        viewModelScope.launch {
            try {
                // Ejecuta la llamada a la API en un contexto de IO
                val result = withContext(Dispatchers.IO) {
                    repository.getBurger()
                }

                // Actualiza el StateFlow con los resultados o con una lista vacía
                _burgers.value = result ?: emptyList()

            } catch (e: Exception) {
                // Manejo de excepciones y comunicación de errores
                _error.value = "Error al obtener datos: ${e.message}"
            }
        }

    }

    var selectedItemIndex by mutableStateOf(0)

    fun onNavigationItemSelected(index: Int) {
        selectedItemIndex = index
    }


    fun getBurgerById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repository.getBurgerById(id)
                state = state.copy(
                    name = result?.name ?: "",
                    price = result?.price ?: 0.0,
                    image = result?.image ?: "",
                    ingredients = result?.ingredients ?: emptyList(),
                    additionalInfo = result?.additionalInfo ?: ""

                )
            }
        }
    }
}