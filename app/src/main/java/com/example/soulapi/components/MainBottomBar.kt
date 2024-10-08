package com.example.soulapi.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.soulapi.R
import com.example.soulapi.model.BottomNavigationItem
import com.example.soulapi.savedLists
import com.example.soulapi.viewModel.SoulViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomBar(
    navController: NavController,
    soulViewModel: SoulViewModel
) {

    val favoriteCount by soulViewModel.favProducts.collectAsState()

    val listCart by savedLists.carlistObversable.collectAsState()

    val items = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.HomeBar),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.FavBar),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.CartBar),
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            hasNews = false,
            badgeCount = if (listCart.isNotEmpty()) listCart.count().toString() else ""
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.SettingsBar),
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = true,
        ),
    )

    // Observar el destino actual de la navegación
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEachIndexed { index, item ->
            val isSelected = when (currentDestination?.route) {
                "HomeView/burger" -> index == 0
                "FavoriteView" -> index == 1
                "CartView" -> index == 2
                "SettingsView" -> index == 3
                else -> false
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    when (index) {
                        0 -> navController.navigate("HomeView/burger") {
                            // Simplificar popUpTo
                            popUpTo("HomeView/burger") { inclusive = false }
                            launchSingleTop = true
                        }
                        1 -> navController.navigate("FavoriteView") {
                            // Simplificar popUpTo
                            popUpTo("HomeView/burger") { inclusive = false }
                            launchSingleTop = true
                        }
                        2 -> navController.navigate("CartView") {
                            popUpTo("HomeView/burger") { inclusive = false }
                            launchSingleTop = true
                        }
                        3 -> navController.navigate("SettingsView") {
                            popUpTo("HomeView/burger") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                label = { Text(text = item.title) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount.isNotEmpty() && item.badgeCount != "0") {
                                Badge {
                                    Text(text = item.badgeCount)
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isSelected) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}