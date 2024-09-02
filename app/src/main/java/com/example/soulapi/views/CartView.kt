package com.example.soulapi.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.soulapi.components.CardCart
import com.example.soulapi.model.CartModel
import com.example.soulapi.model.ProductsModel
import com.example.soulapi.savedLists
import com.example.soulapi.viewModel.CartViewModel


@Composable
fun CartView(
    viewModel: CartViewModel // Pasar el ViewModel directamente
) {
    // Llamar a ContentCartView con el ViewModel
    ContentCartView(viewModel)
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ContentCartView(viewModel: CartViewModel, ) {
    // Obtener la lista de items directamente desde el ViewModel


   /* if (viewModel.cartList.value.isEmpty()) {
        Text(text = "Cargando...", color = Color.Gray)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFFf0f0f0))
        ) {
            items() { item ->
                CardCart(
                    cartModel = item,
                    onRemoveClick = { viewModel.onRemoveClick(item) },
                    onDecrementClick = { viewModel.onDecrementClick(item) },
                    onIncrementClick = { viewModel.onIncrementClick(item) }
                )
            }
        }
    }*/
}