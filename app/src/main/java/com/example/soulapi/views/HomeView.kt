package com.example.soulapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavController
import com.example.soulapi.components.CardBurger
import com.example.soulapi.components.FoodCategories
import com.example.soulapi.components.MainScaffold
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun HomeView(
    viewModel: SoulViewModel,
    navController: NavController,
    filteredProductList: List<ProductsModel>,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color(0xFFf0f0f0))
    ) {
        // Coloca FoodCategories en la parte superior
        FoodCategories(navController)

        // Coloca el contenido de ContentDrinksView debajo de FoodCategories
        ContentDrinksView(viewModel, paddingValues, navController, filteredProductList)
    }
}

@Composable
fun ContentDrinksView(
    viewModel: SoulViewModel,
    pad: PaddingValues,
    navController: NavController,
    productsFiltered: List<ProductsModel>
) {
    val products by viewModel.products.collectAsState()
    val favorites by viewModel.favProducts.collectAsState()

    if (productsFiltered.isEmpty()) {
        Text(text = "Cargando...", color = Color.Gray)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFFf0f0f0))
        ) {
            items(productsFiltered) { item ->
                CardBurger(
                    burger = item,
                    isFavorite = item.id in favorites,
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