package com.example.soulapi.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.soulapi.viewModel.CartViewModel
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun MainScaffold(
    soulViewModel: SoulViewModel,
    navController: NavController,
    showBottomBar: Boolean = true,
    content: @Composable (PaddingValues) -> Unit,
    topBarTitle: String
) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = topBarTitle
            ) {}
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