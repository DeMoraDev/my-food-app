package com.example.soulapi

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsManager {

    private const val PREFS_NAME = "MyAppPrefs"
    private const val FAVORITES_KEY = "favorite_products"

    fun saveFavorites(context: Context, favoriteIds: List<Int>) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val idsString = favoriteIds.joinToString(separator = ",")
        editor.putString(FAVORITES_KEY, idsString)
        editor.apply() // `apply()` guarda en segundo plano
    }

    fun loadFavorites(context: Context): List<Int> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val idsString = sharedPreferences.getString(FAVORITES_KEY, "")

        return idsString
            ?.split(",")
            ?.mapNotNull { it.toIntOrNull() } // Convierte cada elemento a entero, ignorando los valores nulos
            ?: emptyList() // Devuelve una lista vacía si `idsString` es nulo
    }
}