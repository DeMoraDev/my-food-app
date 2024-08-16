package com.example.soulapi.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun <T : ViewModel> MainScaffold(
    soulViewModel: SoulViewModel,
    viewModel: T,
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(title = "SOUL COFFEE BEER") {
                // Acción del botón en el TopBar
            }
        },
        bottomBar = {
            MainBottomBar(soulViewModel.selectedItemIndex) { index ->
                soulViewModel.onNavigationItemSelected(index)
                when (index) {
                    0 -> navController.navigate("HomeView")
                    1 -> navController.navigate("FavoriteView")
                    2 -> navController.navigate("CartView")
                    3 -> navController.navigate("SettingsView")
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}