package com.example.soulapi.views

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soulapi.components.ImageDetail
import com.example.soulapi.components.MainImage
import com.example.soulapi.components.MainTopBar
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.util.Utils
import com.example.soulapi.viewModel.SoulViewModel


@Composable
fun DetailView(viewModel: SoulViewModel, navController: NavController, id: Int) {
    // Recoge el estado actual de los productos
    val products by viewModel.products.collectAsState()

    // Buscar el producto usando el ID pasado
    val product = products.firstOrNull { it.id == id }

    Scaffold(
        topBar = {
            MainTopBar(
                title = product?.nombre_en ?: "Producto no encontrado",
                showBackButton = true
            ) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        if (product != null) {
            ContentDetailView(paddingValues, product)
        } else {
            Text(
                text = "Producto no encontrado",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ContentDetailView(paddingValues: PaddingValues, product: ProductsModel) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color.White)
    ) {
        ImageDetail(imageUrl = product.image)
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = Utils.formatPrice(product.price), fontSize = 30.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Ingredientes:", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            product.ingredients_en?.forEach { ingredient ->
                Text(text = "- $ingredient", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Mostrar la sección de alérgenos
            Text(text = "Alérgenos:", fontSize = 30.sp, color = Color.Black)
            if (product.alergenos_en.isNullOrEmpty()) {
                Text(text = "- No hay alérgenos disponibles", color = Color.Black)
            } else {
                product.alergenos_en?.forEach { alergen ->
                    Text(text = "- $alergen", color = Color.Black)
                }
            }
        }
    }
}