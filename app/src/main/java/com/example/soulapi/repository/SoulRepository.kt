package com.example.soulapi.repository

import com.example.soulapi.AuthRequest
import com.example.soulapi.AuthResponse
import com.example.soulapi.data.SoulApi
import com.example.soulapi.model.SoulModel
import retrofit2.Response
import javax.inject.Inject

class SoulRepository @Inject constructor(private val soulApi: SoulApi) {

    suspend fun getBurger(): List<SoulModel>? {
        val response = soulApi.getBurger()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }

    suspend fun getBurgerById(id: Int): SoulModel? {
        val response = soulApi.getBurgerById(id)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun login(request: AuthRequest): Response<AuthResponse> {
        val response = soulApi.login(request)
        return response

    }

    suspend fun register(request: AuthRequest): AuthResponse? {
        val response = soulApi.register(request)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}
