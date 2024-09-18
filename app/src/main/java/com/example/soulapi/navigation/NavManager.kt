package com.example.soulapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
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
import com.example.soulapi.R

@Composable
fun NavManager(soulViewModel: SoulViewModel) {
    val navController = rememberNavController()
    val products by soulViewModel.products.collectAsState()
    val favorites by soulViewModel.favProducts.collectAsState()

    //Datos para la que las vistas tenga el producto de la promo
    val promoProductId = 11
    val promoProduct = products.find { it.id == promoProductId }


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
                    HomeView(
                        soulViewModel,
                        navController,
                        products.filter { it.tipo == type },
                        paddingValues,
                        promoProduct = promoProduct
                    )
                },
                topBarTitle = stringResource(id = R.string.titleHome)
            )
        }
        composable("PizzasView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(
                        soulViewModel,
                        navController,
                        products.filter { it.tipo == "pizza" },
                        paddingValues,
                        promoProduct = promoProduct
                    )
                },
                topBarTitle = stringResource(id = R.string.titlePizzas)
            )
        }
        composable("SidesView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(
                        soulViewModel,
                        navController,
                        products.filter { it.tipo == "sides" },
                        paddingValues,
                        promoProduct = promoProduct
                    )
                },
                topBarTitle = stringResource(id = R.string.titleSides)
            )
        }
        composable("SaucesView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(
                        soulViewModel,
                        navController,
                        products.filter { it.tipo == "sauce" },
                        paddingValues,
                        promoProduct = promoProduct
                    )
                },
                topBarTitle = stringResource(id = R.string.titleSauces)
            )
        }
        composable("DrinksView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(
                        soulViewModel,
                        navController,
                        products.filter { it.tipo == "drink" },
                        paddingValues,
                        promoProduct = promoProduct
                    )
                },
                topBarTitle = stringResource(id = R.string.titleDrinks)
            )
        }
        composable("FavoriteView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    HomeView(
                        soulViewModel,
                        navController,
                        products.filter { it.id in favorites },
                        paddingValues,
                        promoProduct = null //Favorites comparte el View del HomeView por lo que necesita la promo
                    )
                },
                topBarTitle = stringResource(id = R.string.titleFavorites)
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
                },
                topBarTitle = stringResource(id = R.string.titleDetails)
            )
        }
        composable("CartView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    CartView(CartViewModel(), paddingValues)  // Asegúrate de pasar el paddingValues
                },
                topBarTitle = stringResource(id = R.string.titleCart)
            )
        }
        composable("SettingsView") {
            MainScaffold(
                soulViewModel = soulViewModel,
                navController = navController,
                showBottomBar = true,
                content = { paddingValues ->
                    SettingsView(navController, paddingValues)
                },
                topBarTitle = stringResource(id = R.string.titleProfile)
            )
        }
    }
}

