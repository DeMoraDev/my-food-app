package com.example.soulapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.example.soulapi.R


@Composable
fun DetailView(
    viewModel: SoulViewModel,
    navController: NavController,
    id: Int,
) {
    val products by viewModel.products.collectAsState()
    val product = products.firstOrNull { it.id == id }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (product != null) {
            ContentDetailView(
                product = product,
                navController = navController,
                viewModel = viewModel
            )
        } else {
            Text(
                text = stringResource(id = R.string.productNotFound),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ContentDetailView(
    product: ProductsModel,
    navController: NavController,
    viewModel: SoulViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.LightGray)
        ) {
            ImageDetail(imageUrl = product.image)

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(18.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(18.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /* Acción para agregar a favoritos */ }) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Black
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(y = (-20).dp)
                .background(Color.Transparent)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topStart = 32.dp,
                            topEnd = 32.dp
                        )
                    ), // Esquinas redondeadas superiores
                shape = RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                ), // Forma más redondeada en la parte superior
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp) // Espacio para el botón
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                            .weight(1f) // Ocupa el espacio restante
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = product.nombre_en,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }

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

                        item {
                            Text(
                                text = stringResource(id = R.string.ingredientsText),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                            product.ingredients_en?.forEach { ingredient ->
                                Text(text = "- $ingredient", color = Color.Black)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        item {
                            Text(
                                text = "Alérgenos:",
                                fontSize = 30.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold
                            )
                            if (product.alergenos_en.isNullOrEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.noAlergens),
                                    color = Color.Black
                                )
                            } else {
                                AllergenIcons(allergens = product.alergenos_en)
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }

                    // Botón de agregar al carrito
                    Button(
                        onClick = { viewModel.addList(product) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFA500)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.addToCartButtonText),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}
