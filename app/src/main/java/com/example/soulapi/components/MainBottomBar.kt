package com.example.soulapi.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.soulapi.R
import com.example.soulapi.model.BottomNavigationItem
import com.example.soulapi.viewModel.SoulViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomBar(
    selectedItemIndex: Int,
    onClickItem: (Int) -> Unit,
    soulViewModel: SoulViewModel
) {

    val favoriteCount by soulViewModel.favProducts.collectAsState()
    val favCount = favoriteCount.size

    val items = listOf(
        BottomNavigationItem(
            title = stringResource(id = R.string.HomeBar),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.FavBar),
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            hasNews = false,
            badgeCount = if (favCount > 0) favCount.toString() else ""
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.CartBar),
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = stringResource(id = R.string.SettingsBar),
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
        ),
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { onClickItem(index) },
                label = { Text(text = item.title) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null && item.badgeCount != "0") {
                                Badge {
                                    Text(text = item.badgeCount)
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}
