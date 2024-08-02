package com.example.soulapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soulapi.components.ImageDetail
import com.example.soulapi.components.MainImage
import com.example.soulapi.components.MainTopBar
import com.example.soulapi.util.Utils
import com.example.soulapi.viewModel.SoulViewModel

@Composable
fun DetailView(viewModel: SoulViewModel, navController: NavController, id: Int) {
    LaunchedEffect(Unit) {
        viewModel.getBurgerById(id)
    }
    Scaffold(
        topBar = {
            MainTopBar(title = viewModel.state.name, showBackButton = true) {
                navController.popBackStack()
            }
        }
    ) {
        ContentDetailView(it, viewModel)
    }
}

@Composable
fun ContentDetailView(pad: PaddingValues, viewModel: SoulViewModel) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(pad)
            .background(Color.Black)
    ) {
        ImageDetail(image = state.image)
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = Utils.formatPrice(state.price), fontSize = 30.sp, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Ingredientes:", fontSize = 30.sp, color = Color.Magenta)
            state.ingredients.forEach { ingredient ->
                Text(text = "- $ingredient", color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = state.additionalInfo,
                color = Color.White,
                fontSize = 16.sp, // Ajusta el tamaño de fuente si es necesario
                modifier = Modifier.padding(top = 8.dp) // Espacio superior para separar de otros elementos
            )
        }
    }
}