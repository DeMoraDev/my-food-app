package com.example.soulapi.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soulapi.components.ListProfileContent
import com.example.soulapi.components.LogOutButton
import com.example.soulapi.components.ProfileCard
import com.example.soulapi.viewModel.SettingsViewModel


@Composable
fun SettingsView(
    navController: NavController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(      //Todo El contenido no parece respetar el padding, Card muy pegada al top
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        ProfileCard()

        ListProfileContent()

        Spacer(modifier = Modifier.height(32.dp))

        LogOutButton {
            navController.navigate("login")
        }

        @Composable
        fun ContentSettingsView(viewModel: SettingsViewModel, paddingValues: PaddingValues) {
            Text(text = "SettingsView")
        }
    }
}