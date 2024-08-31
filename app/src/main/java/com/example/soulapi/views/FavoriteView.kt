package com.example.soulapi.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        ContentFavoriteView(soulViewModel, paddingValues, navController)
    }
}

@Composable
fun ContentFavoriteView(
    viewModel: SoulViewModel,
    pad: PaddingValues,
    navController: NavController
){

    val products by viewModel.products.collectAsState()

    val favorites by viewModel.favProducts.collectAsState()
    val productsFiltered = products.filter { it.id in favorites }


    if (productsFiltered.isEmpty()) {
        Text(text = "Cargando...", color = Color.Gray)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFFf0f0f0))
        ) {
            items(productsFiltered) { item ->
                CardBurger(
                    burger = item,
                    isFavorite = item.id in favorites ,
                    onClick = {
                        navController.navigate("DetailView/${item.id}")
                    },
                    onFavoriteClick = {
                        viewModel.addFavoriteProduct(item.id)
                    }
                )
            }
        }
    }
}