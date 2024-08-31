package com.example.soulapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.soulapi.viewModel.CartViewModel
import com.example.soulapi.viewModel.FavoriteViewModel
import com.example.soulapi.viewModel.SettingsViewModel
import com.example.soulapi.viewModel.SoulViewModel
import com.example.soulapi.views.CartView
import com.example.soulapi.views.DetailView
import com.example.soulapi.views.DrinksView
import com.example.soulapi.views.FavoriteView
import com.example.soulapi.views.HomeView
import com.example.soulapi.views.LoginView
import com.example.soulapi.views.PizzasView
import com.example.soulapi.views.RegisterView
import com.example.soulapi.views.SaucesView
import com.example.soulapi.views.SettingsView
import com.example.soulapi.views.SidesView

@Composable
fun NavManager(soulViewModel: SoulViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginView(navController)
        }
        composable("RegisterView") {
            RegisterView(navController)
        }
        composable("HomeView") {
            HomeView(soulViewModel, navController)
        }
        composable("DetailView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(soulViewModel, navController, id)
        }
        composable("FavoriteView") {
            FavoriteView(soulViewModel, navController)
        }
        composable("CartView") {
            CartView(soulViewModel, navController)
        }
        composable("SettingsView") {
            SettingsView(soulViewModel, navController)
        }
        composable("PizzasView") {
            PizzasView(soulViewModel, navController)
        }
        composable("SidesView") {
            SidesView(soulViewModel, navController)
        }
        composable("SaucesView") {
            SaucesView(soulViewModel, navController)
        }
        composable("DrinksView") {
            DrinksView(soulViewModel, navController)
        }
    }
}
