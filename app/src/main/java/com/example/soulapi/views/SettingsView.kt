package com.example.soulapi.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soulapi.components.MainScaffold
import com.example.soulapi.viewModel.SettingsViewModel
import com.example.soulapi.viewModel.SoulViewModel


@Composable
fun SettingsView(
    soulViewModel: SoulViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {
    val viewModel: SettingsViewModel = hiltViewModel()


}

@Composable
fun ContentSettingsView(viewModel: SettingsViewModel, paddingValues: PaddingValues) {
    Text(text = "SettingsView")
}