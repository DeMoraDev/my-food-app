package com.example.soulapi.data

import com.example.soulapi.AuthRequest
import com.example.soulapi.AuthResponse
import com.example.soulapi.model.SoulModel
import com.example.soulapi.util.Constants.Companion.ENDPOINT
import com.example.soulapi.util.Constants.Companion.LOGIN_ENDPOINT
import com.example.soulapi.util.Constants.Companion.REGISTER_ENDPOINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SoulApi {

    @GET(ENDPOINT)
    suspend fun getBurger(): Response<List<SoulModel>>

    @GET("$ENDPOINT/{id}")
    suspend fun getBurgerById(@Path(value = "id")id:Int) : Response<SoulModel>

    @POST(LOGIN_ENDPOINT)
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST(REGISTER_ENDPOINT)
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>
}