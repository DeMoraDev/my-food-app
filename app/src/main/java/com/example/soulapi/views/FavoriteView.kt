package com.example.soulapi.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
        ContentFavoriteView(soulViewModel, paddingValues)
    }
}

@Composable
fun ContentFavoriteView(viewModel: SoulViewModel, paddingValues: PaddingValues) {

    val images by viewModel.imagenes.collectAsState()


    if (images.isEmpty()) {
        Text(text = "Cargando imágenes...", color = Color.Black)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Ajusta el número de columnas según tu diseño
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color(0xFFf0f0f0))
        ) {
            items(images) { painter ->
                // Cada celda en la cuadrícula muestra una imagen
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(4.dp)
                )
            }
        }
    }
}
