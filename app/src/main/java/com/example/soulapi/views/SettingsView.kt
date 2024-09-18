package com.example.soulapi.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soulapi.components.ListProfileContent
import com.example.soulapi.components.LogOutButton
import com.example.soulapi.components.ProfileCard


@Composable
fun SettingsView(
    navController: NavController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        ContentSettingsView(navController, paddingValues)
    }
}

@Composable
fun ContentSettingsView(navController: NavController, paddingValues: PaddingValues) {

    Spacer(modifier = Modifier.height(9.dp))

    ProfileCard()

    Spacer(modifier = Modifier.height(9.dp))

    ListProfileContent()

    Spacer(modifier = Modifier.height(32.dp))

    LogOutButton {
        navController.navigate("login") {
            popUpTo("Login") { inclusive = true }
        }
    }
}