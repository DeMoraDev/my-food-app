package com.example.soulapi.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        Text(
            text = "El carrito está vacío",
            color = Color.Gray,
            modifier = Modifier
                .fillMaxSize()
        )
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