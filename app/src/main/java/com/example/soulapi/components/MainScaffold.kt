package com.example.soulapi.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun MainScaffold(
    soulViewModel: SoulViewModel,
    navController: NavController,
    showBottomBar: Boolean = true, // Controla la visibilidad del BottomBar
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(title = "SOUL COFFEE BEER") {
                // Acción del botón en el TopBar
            }
        },
        bottomBar = {
            if (showBottomBar) {
                MainBottomBar(
                    navController = navController,
                    soulViewModel = soulViewModel
                )
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}