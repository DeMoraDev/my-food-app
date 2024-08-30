package com.example.soulapi.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.soulapi.viewModel.SoulViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.soulapi.R
import com.example.soulapi.model.CartCardModel
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.util.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, showBackButton: Boolean = false, onClickBackButton: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, color = Color.Black, fontWeight = FontWeight.ExtraBold) },
        /*colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF2B2626)
        )*/
        modifier = Modifier.shadow(4.dp),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onClickBackButton() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Usuario",
                tint = Color.Black,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp)
                    .clickable { }
            )
        }
    )
}

@Composable
fun CardBurger(burger: ProductsModel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            //.shadow(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ensure MainImage is at the top without any extra padding
            MainImage(imageUrl = burger.image)

            // Use padding only on the text elements
            Text(
                text = burger.nombre_en,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 6.dp, start = 8.dp, end = 8.dp)
                    .height(60.dp)
            )
            Text(
                text = Utils.formatPrice(burger.price),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}

@Composable
fun MainImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl.replace("localhost", "10.0.2.2"))//todo Reviver
            .crossfade(true)
            .build()
    )

    // Verificar el estado de la carga
    when (val result = painter.state) {
        is AsyncImagePainter.State.Loading -> Log.d("ImageLoad", "Loading image")
        is AsyncImagePainter.State.Error -> Log.e("ImageLoad", "Error loading image: ${result.result}")
        is AsyncImagePainter.State.Success -> Log.d("ImageLoad", "Image loaded successfully")
        else -> {}
    }

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    )
}

@Composable
fun ImageDetail(image: String) {
    val painter = rememberAsyncImagePainter(model = image)

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun FoodCategories() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        CategoryCard(
            iconResId = R.drawable.burgericon,
            title = "Burgers",
            onClick = { }
        )


        CategoryCard(
            iconResId = R.drawable.pizzaicon,
            title = "Pizzas",
            onClick = { }
        )


        CategoryCard(
            iconResId = R.drawable.bebidaicon,
            title = "Bebidas",
            onClick = { }
        )
    }
}

@Composable
fun CategoryCard(
    @DrawableRes iconResId: Int,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() },

        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun CartItemCard(cartItems: CartCardModel) {

    val quantity = remember { mutableStateOf(cartItems.initialQuantity) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = cartItems.productName,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        if (quantity.value > 1) {
                            quantity.value -= 1
                            cartItems.onQuantityChanged(quantity.value)
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.btn_minus),
                            contentDescription = "Restar cantidad"
                        )
                    }
                    Text(
                        text = quantity.value.toString(),
                        fontSize = 16.sp
                    )
                    IconButton(onClick = {
                        quantity.value += 1
                        cartItems.onQuantityChanged(quantity.value)
                    }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.btn_plus),
                            contentDescription = "Sumar cantidad"
                        )
                    }
                }
            }

            Text(
                text = cartItems.productPrice,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )

            Box(
                modifier = Modifier.size(60.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = cartItems.productImage),
                    contentDescription = "Product image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
                IconButton(
                    onClick = cartItems.onRemoveItem,
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Red, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_delete),
                        contentDescription = "Borrar item",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
