package com.example.soulapi.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soulapi.R
import com.example.soulapi.components.CardCart
import com.example.soulapi.util.Utils
import com.example.soulapi.viewModel.CartViewModel


@Composable
fun CartView(
    viewModel: CartViewModel,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)  // Aplica el padding aquí
    ) {
        ContentCartView(viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ContentCartView(viewModel: CartViewModel) {
    val cartItems by viewModel.cartList.collectAsState()

    if (cartItems.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.carticon),
                contentDescription = "Carrito vacío",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tu carrito está vacío",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Empieza a comprar y disfruta comiendo",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFf0f0f0))
        ) {
            items(cartItems) { item ->
                val quantity by item.quantity.collectAsState()
                CardCart(
                    productName = item.product.nombre_en,
                    productPrice = Utils.formatPrice(item.product.price),
                    imageUrl = item.product.image,
                    quantity = quantity,
                    onRemoveClick = { viewModel.onRemoveClick(item) },
                    onDecrementClick = { viewModel.onDecrementClick(item) },
                    onIncrementClick = { viewModel.onIncrementClick(item) }
                )
            }
        }
    }
}