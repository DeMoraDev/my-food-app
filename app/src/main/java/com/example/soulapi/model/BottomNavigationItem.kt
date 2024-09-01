package com.example.soulapi.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: String = "0"
)