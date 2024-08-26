package com.example.soulapi.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soulapi.components.CardBurger
import com.example.soulapi.components.CartItemCard
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
        ContentCartView(viewModel, soulViewModel, paddingValues)
    }
}

@Composable
fun ContentCartView(viewModel: CartViewModel,soulViewModel: SoulViewModel, paddingValues: PaddingValues) {
    val burgers by soulViewModel.burgers.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color(0xFFf0f0f0))
    ) {
        items(burgers) { item ->

        }
    }
}