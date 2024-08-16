package com.example.soulapi.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soulapi.components.MainScaffold
import com.example.soulapi.viewModel.CartViewModel
import com.example.soulapi.viewModel.SoulViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartView(soulViewModel: SoulViewModel, navController: NavController) {
    val viewModel: CartViewModel = hiltViewModel()

    MainScaffold(
        soulViewModel = soulViewModel,
        viewModel = viewModel,
        navController = navController
    ) { paddingValues ->
        ContentCartView(viewModel, paddingValues)
    }
}

@Composable
fun ContentCartView(viewModel: CartViewModel, paddingValues: PaddingValues) {
    Text(text = "CartView")
}