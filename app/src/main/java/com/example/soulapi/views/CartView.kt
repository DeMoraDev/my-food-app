package com.example.soulapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soulapi.savedLists
import com.example.soulapi.components.CardCart


@Composable
fun CartView(

) {
    ContentCartView()

}

@Composable
fun ContentCartView(

) {

    if (savedLists.cartList.isEmpty()) {
        Text(text = "Cargando...", color = Color.Gray)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFFf0f0f0))
        ) {
            items(savedLists.cartList) { item ->
                CardCart(
                    cartModel = item
                )
            }
        }
    }
}


