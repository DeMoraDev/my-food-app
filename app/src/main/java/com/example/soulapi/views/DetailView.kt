package com.example.soulapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soulapi.components.AllergenIcons
import com.example.soulapi.components.ImageDetail
import com.example.soulapi.components.MainTopBar
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.util.Utils
import com.example.soulapi.viewModel.SoulViewModel


@Composable
fun DetailView(
    viewModel: SoulViewModel,
    navController: NavController,
    id: Int,
    paddingValues: PaddingValues
) {
    val products by viewModel.products.collectAsState()
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
    ) { innerPaddingValues ->
        // Usa un Box para aplicar padding alrededor del contenido y asegurar que el LazyColumn tenga suficiente espacio
        Box(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize()
        ) {
            if (product != null) {
                ContentDetailView(product = product, viewModel = viewModel, paddingValues)
            } else {
                Text(
                    text = "Producto no encontrado",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ContentDetailView(product: ProductsModel, viewModel: SoulViewModel, paddingValues: PaddingValues) {
    // Asegúrate de que el LazyColumn tenga suficiente espacio para el scroll
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
    ) {
        // Imágen del producto
        item {
            ImageDetail(imageUrl = product.image)
        }

        // Nombre del producto
        item {
            Text(text = product.nombre_en, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Precio del producto
        item {
            Text(
                text = Utils.formatPrice(product.price),
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Ingredientes
        item {
            Text(
                text = "Ingredientes:",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            product.ingredients_en?.forEach { ingredient ->
                Text(text = "- $ingredient", color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Alérgenos
        item {
            Text(
                text = "Alérgenos:",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
            if (product.alergenos_en.isNullOrEmpty()) {
                Text(text = "- No hay alérgenos disponibles", color = Color.Black)
            } else {
                AllergenIcons(allergens = product.alergenos_en)
            }
            Spacer(modifier = Modifier.height(5.dp))
        }

        // Botón de agregar al carrito
        item {
            Button(
                onClick = { viewModel.addList(product) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA500)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp) // Padding adicional alrededor del botón
            ) {
                Text(text = "Add to Cart", fontSize = 20.sp)
            }
        }
    }
}
