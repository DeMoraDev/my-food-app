package com.example.soulapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.soulapi.components.MainScaffold
import com.example.soulapi.viewModel.CartViewModel
import com.example.soulapi.viewModel.SoulViewModel
import com.example.soulapi.views.CartView
import com.example.soulapi.views.DetailView
import com.example.soulapi.views.HomeView
import com.example.soulapi.views.LoginView
import com.example.soulapi.views.RegisterView
import com.example.soulapi.views.SettingsView
import com.example.soulapi.views.SplashScreen

@Composable
fun NavManager(soulViewModel: SoulViewModel) {
    val navController = rememberNavController()
    val products by soulViewModel.products.collectAsState()
    val favorites by soulViewModel.favProducts.collectAsState()


    NavHost(navController = navController, startDestination = "SplashScreen") {

        // Destinos que no usan el Scaffold
        composable("Login") {
            LoginView(navController)
        }
        composable("RegisterView") {
            RegisterView(navController)
        }
        composable("SplashScreen") {
            SplashScreen(navController)
        }

        // Destinos que usan el Scaffold
        composable("HomeView/{type}", arguments = listOf(
            navArgument("type") { defaultValue = "burger" }
        )) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "burger"
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(soulViewModel, navController, products.filter { it.tipo == type }, paddingValues)
                }
            )
        }
        composable("PizzasView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(soulViewModel, navController, products.filter { it.tipo == "pizza" }, paddingValues)
                }
            )
        }
        composable("SidesView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(soulViewModel, navController, products.filter { it.tipo == "sides" }, paddingValues)
                }
            )
        }
        composable("SaucesView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(soulViewModel, navController, products.filter { it.tipo == "sauce" }, paddingValues)
                }
            )
        }
        composable("DrinksView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(soulViewModel, navController, products.filter { it.tipo == "drink" }, paddingValues)
                }
            )
        }
        composable("FavoriteView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(soulViewModel, navController, products.filter { it.id in favorites }, paddingValues)
                }
            )
        }
        composable("DetailView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    DetailView(soulViewModel, navController, id, paddingValues)
                }
            )
        }
        composable("CartView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    CartView(CartViewModel(), paddingValues)  // Asegúrate de pasar el paddingValues
                }
            )
        }
        composable("SettingsView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    SettingsView(navController, paddingValues)
                }
            )
        }
    }
}

