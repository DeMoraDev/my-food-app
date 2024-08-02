package com.example.soulapi.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.AuthRequest
import com.example.soulapi.AuthResponse
import com.example.soulapi.ResultState
import com.example.soulapi.model.SoulModel
import kotlinx.coroutines.launch
import com.example.soulapi.repository.SoulRepository
import com.example.soulapi.state.BurgerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONException
import org.json.JSONObject

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: SoulRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<ResultState<AuthResponse>>(ResultState.Idle)
    val loginState: StateFlow<ResultState<AuthResponse>> = _loginState.asStateFlow()

    // StateFlow para manejar errores
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    var state by mutableStateOf(BurgerState())
        private set


    fun login(username: String, password: String) {
        val request = AuthRequest(username = username, password = password)
        viewModelScope.launch {
            _loginState.value = ResultState.Loading
            try {
                val response = repository.login(request)
                if (response.isSuccessful) {
                    val authResponse = response.body()
                    if (authResponse != null && authResponse.error == null) {
                        _loginState.value = ResultState.Success(authResponse)
                    } else {
                        // Extraer el mensaje de error del JSON
                        val errorMessage = authResponse?.error ?: "Unknown error occurred"
                        _loginState.value = ResultState.Error(errorMessage)
                    }
                } else {
                    // Extraer el mensaje de error del cuerpo de la respuesta si está disponible
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = errorResponse?.let {
                        // Analizar el JSON de error
                        try {
                            val jsonObject = JSONObject(it)
                            jsonObject.getString("error")
                        } catch (e: JSONException) {
                            "Unknown error occurred"
                        }
                    } ?: "Unknown error occurred"
                    _loginState.value = ResultState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _loginState.value = ResultState.Error("Network error: ${e.message}")
            }
        }
    }
}
fun register() {
    val request = AuthRequest(username = "newuser", password = "newpassword")
    // viewModelScope.launch {
    try {
        // Make the login API call
        //  val response = repository.register(request)
        // val a = response
        // Check the response
        /* if (response.isSuccessful) {
            val authResponse = response.body()
            if (authResponse != null && authResponse.error == null) {
                println("Login successful: ${authResponse.message}")
                if (authResponse.admin == true) {
                    println("User is an admin.")
                } else {
                    println("User is a regular user.")
                }
            } else {
                println("Login error: ${authResponse?.error}")
            }
        } else {
            println("Request failed: ${response.code()}")
        }

         */
    } catch (e: Exception) {
        println("Network error: ${e.message}")
    }

}