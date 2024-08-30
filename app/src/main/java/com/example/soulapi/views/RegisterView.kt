package com.example.soulapi.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soulapi.AuthResponse
import com.example.soulapi.R
import com.example.soulapi.ResultState
import com.example.soulapi.viewModel.LoginViewModel
import com.example.soulapi.viewModel.RegisterViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun RegisterView(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {

    Column {
        ContentRegisterView(navController, viewModel)
    }

}

@Composable
fun ContentRegisterView(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val registerState by viewModel.registerState.collectAsState()
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf0f0f0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logolight),
            contentDescription = "",
            modifier = Modifier
                .size(250.dp)
        )
        Text(
            text = "Únete ahora y disfruta comiendo",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "E-mail",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { newEmail ->
                    username = newEmail
                },
                label = {
                    Text(text = "Email")
                },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { newPass ->
                    password = newPass
                },
                label = {
                    Text(text = "Contraseña")
                },
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) {
                        painterResource(id = R.drawable.view)
                    } else {
                        painterResource(id = R.drawable.hidden)
                    }
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(painter = image, contentDescription = null, modifier = Modifier.size(24.dp))
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Confirm Password",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { newPass ->
                    confirmPassword = newPass
                },
                label = {
                    Text(text = "Confirmar Contraseña")
                },
                textStyle = TextStyle(color = Color.Black),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) {
                        painterResource(id = R.drawable.view)
                    } else {
                        painterResource(id = R.drawable.hidden)
                    }
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(painter = image, contentDescription = null, modifier = Modifier.size(24.dp))
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (registerState) {
            is ResultState.Loading -> {
                // Mostrar un indicador de carga
                CircularProgressIndicator(color = Color.White)
            }

            is ResultState.Success -> {
                // Manejar el éxito (por ejemplo, navegar a otra pantalla o mostrar un mensaje de éxito)
                val authResponse = (registerState as ResultState.Success).data
                // Aquí podrías navegar o limpiar los campos
            }

            is ResultState.Error -> {
                // Mostrar el mensaje de error
                val errorMessage = (registerState as ResultState.Error).message
                Text(text = errorMessage, color = Color.Red)
                // Opcional: podrías resetear el estado aquí si es necesario
            }

            is ResultState.Idle -> {
                // Mostrar el botón de registro en estado normal
                Button(onClick = {
                    if (username.isNotBlank() && password.isNotBlank() && password == confirmPassword) {
                        viewModel.register(username, password)
                    } else {
                        var errorMessage = "Campos inválidos o contraseñas no coinciden"
                    }
                }) {
                    Text(text = "Register")
                }
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        TextButton(onClick = { navController.navigate("Login") }) {
            Text(text = "Already have an account? Sign in", color = Color(0xFF2196F3))
        }
    }
}