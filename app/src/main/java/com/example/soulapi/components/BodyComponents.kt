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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
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
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.util.Utils
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    showBackButton: Boolean = false,
    onClickBackButton: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .shadow(4.dp)
            .background(Color(0xFFf0f0f0)),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onClickBackButton() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    )
}

@Composable
fun CardBurger(
    burger: ProductsModel,
    onClick: () -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onAddToCart: () -> Unit
) {

    val productName = when (Locale.getDefault().language) {
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
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.Favorite,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = Utils.formatPrice(burger.price),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFFFA500), shape = CircleShape)  // Color naranja claro
                        .clickable(onClick = { onAddToCart() }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardCart(
    productName: String,
    productPrice: String,
    imageUrl: String,
    quantity: Int,
    onRemoveClick: () -> Unit,
    onDecrementClick: () -> Unit,
    onIncrementClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Parte 1: Imagen
            CartImage(
                imageUrl = imageUrl
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Parte 2: Nombre del producto y precio
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = productName,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = productPrice,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Parte 3: Icono de eliminación y botones de cantidad
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {

                IconButton(
                    onClick = onRemoveClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = Color.Gray
                    )
                }

                Box(modifier = Modifier.height(24.dp)) {
                    Spacer(modifier = Modifier.width(24.dp))  // Usa Box para asegurar el espacio
                }

                Box(
                    modifier = Modifier
                        .size(108.dp, 39.dp)
                        .background(
                            Color(0xFFEDEDED),
                            shape = RoundedCornerShape(50)
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(vertical = 7.5.dp)
                        ) {
                            IconButton(
                                onClick = onDecrementClick,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Decrement",
                                    tint = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = quantity.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFA500))
                                .padding(vertical = 7.5.dp)
                        ) {
                            IconButton(
                                onClick = onIncrementClick,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Increment",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TotalCartCard(
    total: Double,
    discount: Double? = null,
    totalPayment: Double
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            ),
        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 32.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Order Summary",
                modifier = Modifier.padding(bottom = 18.dp),
                fontWeight = FontWeight.ExtraBold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "€${"%.2f".format(total)}",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Delivery",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "€2.00",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            discount?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Discount",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "-€${"%.2f".format(it)}",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Total Payment",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "€${"%.2f".format(totalPayment)}",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Button(
                onClick = { /* TODO: Add checkout functionality */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFFFFA500)
                )
            ) {
                Text(text = "Checkout")
            }
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
fun CartImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl.replace("localhost", "10.0.2.2")) // Ajuste para localhost
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
        contentDescription = "Product Image for cart",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun ImageDetail(imageUrl: String) {
    val painter = rememberAsyncImagePainter(model = imageUrl.replace("localhost", "10.0.2.2"))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
    ) {
        Image(
            painter = painter,
            contentDescription = "Image for detailView",
            contentScale = ContentScale.Crop, // Recorta la imagen para llenar el contenedor
            modifier = Modifier
                .matchParentSize()
                .offset(y = (-50).dp)
        )
    }

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
                        0 -> navController.navigate("HomeView/burger")
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

@Composable
fun PromoCard(onOrderNow: () -> Unit, burger: ProductsModel) {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.promo)

    val productName = when (Locale.getDefault().language) {
        "es" -> burger.nombre_es
        "en" -> burger.nombre_en
        else -> burger.nombre_en
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp)
            .clickable { onOrderNow() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(id = R.string.promoMessage),
                        color = Color.White,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = productName,
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
