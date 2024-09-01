package com.example.soulapi.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.soulapi.R
import com.example.soulapi.model.CartCardModel
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.util.Utils
import java.util.Locale

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
fun CardBurger(burger: ProductsModel, onClick: () -> Unit, isFavorite: Boolean, onFavoriteClick: () -> Unit) {

    val productName = when (Locale.getDefault().language) { // Obtener idioma del sistema
        "es" -> burger.nombre_es
        "en" -> burger.nombre_en
        else -> burger.nombre_en
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Place the image at the top
                MainImage(imageUrl = burger.image)

                // Add the favorite icon in the top right corner
                IconButton(
                    onClick = { onFavoriteClick() },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }

            // Use padding only on the text elements
            Text(
                text = productName,
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
        is AsyncImagePainter.State.Error -> Log.e(
            "ImageLoad",
            "Error loading image: ${result.result}"
        )

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
fun ImageDetail(imageUrl: String) {
    val painter = rememberAsyncImagePainter(model = imageUrl.replace("localhost", "10.0.2.2"))

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp) // Ajusta la altura según sea necesario
            .border(1.dp, Color.Gray) // Añade un borde para la depuración
    )

    // Manejo de errores y estado de carga
    when (painter.state) {
        is AsyncImagePainter.State.Loading -> {

        }
        is AsyncImagePainter.State.Error -> {
            // Mostrar un mensaje de error o una imagen de error
            Text(
                text = "Error al cargar imagen",
                color = Color.Red,
            )
        }
        else -> {
            // Estado por defecto cuando la imagen se ha cargado
        }
    }
}

@Composable
fun FoodCategories(navController: NavController) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(5) { index ->
            CategoryCard(
                iconResId = when (index) {
                    0 -> R.drawable.burgericon
                    1 -> R.drawable.pizzaicon
                    2 -> R.drawable.sidesicon
                    3 -> R.drawable.sauceicon
                    4 -> R.drawable.bebidaicon
                    else -> R.drawable.burgericon
                },
                title = when (index) {
                    0 -> stringResource(id = R.string.burger)
                    1 -> stringResource(id = R.string.pizza)
                    2 -> stringResource(id = R.string.sides)
                    3 -> stringResource(id = R.string.sauces)
                    4 -> stringResource(id = R.string.drinks)
                    else -> stringResource(id = R.string.unknown)
                },
                onClick = {
                    when (index) {
                        0 -> navController.navigate("HomeView")
                        1 -> navController.navigate("PizzasView")
                        2 -> navController.navigate("SidesView")
                        3 -> navController.navigate("SaucesView")
                        4 -> navController.navigate("DrinksView")
                    }
                }
            )
        }
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

@Composable
fun getIconForAllergen(allergen: String): Painter {
    val context = LocalContext.current
    val iconRes = when (allergen.lowercase()) {
        "fish", "pescado" -> R.drawable.fish
        "lupins", "altramuces" -> R.drawable.lupins
        "celery", "apio" -> R.drawable.celery
        "crustaceans", "crustaceo" -> R.drawable.crustaceans
        "sulfur dioxide", "sulphites", "dioxido azufre", "sulfitos" -> R.drawable.sulfur
        "peel fruits", "frutos cascara" -> R.drawable.peelfruit
        "gluten" -> R.drawable.gluten
        "peanuts", "cacahuetes" -> R.drawable.peanuts
        "sesame grains", "granos sesamo" -> R.drawable.sesamo
        "eggs", "huevos" -> R.drawable.eggs
        "dairy", "lacteos" -> R.drawable.dairy
        "mollusks", "moluscos" -> R.drawable.mollusks
        "mustard", "mostaza" -> R.drawable.mustard
        "soy", "soja" -> R.drawable.soy
        else -> R.drawable.ic_launcher_background// Un icono por defecto si no hay coincidencia
    }
    return painterResource(id = iconRes)
}

@Composable
fun AllergenIcons(allergens: List<String>) {
    Row(modifier = Modifier.padding(8.dp)) {
        allergens.forEach { allergen ->
            Image(
                painter = getIconForAllergen(allergen),
                contentDescription = allergen,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
            )
        }
    }
}