package com.example.soulapi.repository

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.soulapi.AuthRequest
import com.example.soulapi.AuthResponse
import com.example.soulapi.data.SoulApi
import com.example.soulapi.model.ImagesModel
import com.example.soulapi.model.ProductsModel
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
    suspend fun getImages(): List<ImageBitmap> {
        val response = soulApi.getImages()

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.map { imageModel ->
                decodeBase64ToImageBitmap(imageModel.image)
                    ?: throw Exception("Error al decodificar la imagen con id: ${imageModel.id}")
            }
        } else {
            throw Exception("Error al obtener las imágenes")
        }
    }

    suspend fun getProducts(): List<ProductsModel> {
        val response = soulApi.getProducts()

        Log.d("API Response", "Code: ${response.code()}, Message: ${response.message()}")

        if (response.body() == null) {
            Log.e("API Response", "Response body es null")
        } else {
            Log.d("API Response", "Response body: ${response.body()}")
        }

        if (response.isSuccessful && response.body() != null) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error al obtener los productos")
        }
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

    private fun decodeBase64ToImageBitmap(base64String: String): ImageBitmap? {
        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return bitmap?.asImageBitmap()
    }
}
