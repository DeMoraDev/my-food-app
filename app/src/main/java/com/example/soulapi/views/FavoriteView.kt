package com.example.soulapi.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soulapi.components.MainScaffold
import com.example.soulapi.viewModel.FavoriteViewModel
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun FavoriteView(soulViewModel: SoulViewModel, navController: NavController) {
    val viewModel: FavoriteViewModel = hiltViewModel()

    MainScaffold(
        soulViewModel = soulViewModel,
        viewModel = viewModel,
        navController = navController
    ) { paddingValues ->
        ContentFavoriteView(viewModel, paddingValues)
    }
}

@Composable
fun ContentFavoriteView(viewModel: FavoriteViewModel, paddingValues: PaddingValues) {
    Text(text = "FavoriteView")
}