package com.example.soulapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soulapi.AuthRequest
import com.example.soulapi.AuthResponse
import com.example.soulapi.ResultState
import com.example.soulapi.repository.SoulRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: SoulRepository) : ViewModel() {

    private val _registerState = MutableStateFlow<ResultState<AuthResponse>>(ResultState.Idle)
    val registerState: StateFlow<ResultState<AuthResponse>> = _registerState.asStateFlow()

    fun register(username: String, password: String) {

        val request = AuthRequest(username = username, password = password)

        viewModelScope.launch {
            try {
                val authResponse = repository.register(request)

                if (authResponse != null && authResponse.error == null) {
                    println("Registration successful: ${authResponse.message}")

                } else {
                    val errorMessage = authResponse?.error ?: "Unknown error occurred"
                    println("Registration error: $errorMessage")
                }
            } catch (e: Exception) {
                println("Network error: ${e.message}")
            }
        }
    }
}