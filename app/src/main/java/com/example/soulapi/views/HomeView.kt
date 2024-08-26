package com.example.soulapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soulapi.components.CardBurger
import com.example.soulapi.components.FoodCategories
import com.example.soulapi.components.MainBottomBar
import com.example.soulapi.components.MainScaffold
import com.example.soulapi.components.MainTopBar
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun HomeView(viewModel: SoulViewModel, navController: NavController) {
    MainScaffold(
        soulViewModel = viewModel,
        viewModel = viewModel,
        navController = navController
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFf0f0f0))
        ) {
            // Coloca FoodCategories en la parte superior
            FoodCategories()

            // Coloca el contenido de ContentHomeView debajo de FoodCategories
            ContentHomeView(viewModel, paddingValues, navController)
        }
    }
}


@Composable
fun ContentHomeView(viewModel: SoulViewModel, pad: PaddingValues, navController: NavController) {

    val burgers by viewModel.burgers.collectAsState()


    if (burgers.isEmpty()) {
        Text(text = "Cargando...", color = Color.Gray)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFFf0f0f0))
        ) {
            items(burgers) { item ->
                CardBurger(item) {
                    navController.navigate("DetailView/${item.id}")
                }

            }
        }
    }
}