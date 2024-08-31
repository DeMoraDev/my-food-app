package com.example.soulapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.soulapi.viewModel.SoulViewModel
import com.example.soulapi.views.CartView
import com.example.soulapi.views.DetailView
import com.example.soulapi.views.HomeView
import com.example.soulapi.views.LoginView
import com.example.soulapi.views.RegisterView
import com.example.soulapi.views.SettingsView

@Composable
fun NavManager(soulViewModel: SoulViewModel) {
    val navController = rememberNavController()

    val products by soulViewModel.products.collectAsState()
    val favorites by soulViewModel.favProducts.collectAsState()

    NavHost(navController = navController, startDestination = "Login") {
        composable("Login") {
            LoginView(navController)
        }
        composable("RegisterView") {
            RegisterView(navController)
        }


        composable("HomeView") {
            HomeView(soulViewModel, navController,  products.filter { it.tipo == "burger" })
        }
        composable("PizzasView") {
            HomeView(soulViewModel, navController,  products.filter { it.tipo == "pizza" })
        }
        composable("SidesView") {
            HomeView(soulViewModel, navController,  products.filter { it.tipo == "sides" })
        }
        composable("SaucesView") {
            HomeView(soulViewModel, navController,  products.filter { it.tipo == "sauce" })
        }
        composable("DrinksView") {
            HomeView(soulViewModel, navController,  products.filter { it.tipo == "drink" })
        }
        composable("FavoriteView") {
            HomeView(soulViewModel, navController,  products.filter { it.id in favorites })
        }


        composable("DetailView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            DetailView(soulViewModel, navController, id)
        }




        composable("CartView") {
            CartView(soulViewModel, navController)
        }
        composable("SettingsView") {
            SettingsView(soulViewModel, navController)
        }

    }
}
