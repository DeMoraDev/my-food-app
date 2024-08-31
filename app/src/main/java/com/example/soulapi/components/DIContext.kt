package com.example.soulapi.components

import android.content.Context
import com.example.soulapi.SoulApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(application: SoulApplication): Context {
        return application.applicationContext
    }
}