package com.example.soulapi.di

import com.example.soulapi.data.SoulApi
import com.example.soulapi.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        // Create an instance of the HttpLoggingInterceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            // Set the logging level to BODY to print full request/response data
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Build the OkHttpClient and add the interceptor
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        // Use the OkHttpClient with the logging interceptor
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiGames(retrofit: Retrofit): SoulApi {
        return retrofit.create(SoulApi::class.java)
    }
}