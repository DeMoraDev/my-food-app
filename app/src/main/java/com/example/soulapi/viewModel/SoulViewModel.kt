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

    // Valores para las burgers
    private val _burgers = MutableStateFlow<List<SoulModel>>(emptyList())
    val burgers = _burgers.asStateFlow()

    // Valores para las imágenes
    private val _imagenes = MutableStateFlow<List<Painter>>(emptyList())
    val imagenes: StateFlow<List<Painter>> get() = _imagenes

    // StateFlow para manejar errores
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    var state by mutableStateOf(BurgerState())
        private set

    init {
        fetchBurgers()
        //Log.d("Base64Data", "Data: $this")
        fetchImages()
    }

    fun fetchBurgers() {
        viewModelScope.launch {
            try {
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


    //Método de fetchImage
    fun fetchImages() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.getImages() // Devuelve una lista de `ImageBitmap`
                }
                _imagenes.value = result.map { BitmapPainter(it) }
            } catch (e: Exception) {
                // Maneja cualquier error aquí
            }
        }

    }
}

