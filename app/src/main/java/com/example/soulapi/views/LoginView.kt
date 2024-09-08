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
import androidx.compose.ui.res.stringResource
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soulapi.R
import com.example.soulapi.ResultState
import com.example.soulapi.viewModel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false)}

    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf0f0f0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.iconlightmode),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.welcome_message),
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail ->
                email = newEmail
            },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            textStyle = TextStyle(color = Color.Black)
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { newPass ->
                password = newPass
            },
            label = {
                Text(text = stringResource(id = R.string.password))
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
                    Icon(painter = image, contentDescription = null, modifier = Modifier.size(20.dp))
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(3.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.forgotten_password), color = Color.Black)
        }
        Button(onClick = {
            if (email.isNotBlank() && password.isNotBlank()) { //Hay que tener en cuenta que sean cosas válidas
                viewModel.login(email, password)
            } else {
                // Manejar campos vacíos aquí, por ejemplo, mostrar un mensaje de error
            }
        }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_account_yet),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
            TextButton(onClick = {
                navController.navigate("RegisterView")
            }) {
                Text(
                    text = stringResource(id = R.string.make_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2196F3)
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("HomeView/burger") }) {
            Text(text = stringResource(id = R.string.login_as_guest))
        }

        when (loginState) {
            is ResultState.Loading -> {
                CircularProgressIndicator()
            }
            is ResultState.Success -> {
                LaunchedEffect(Unit) {
                    // Navegar a HomeView si el login es exitoso
                    navController.navigate("HomeView/burger")
                }
                Text(text = "Login successful!", color = Color.Green)
            }
            is ResultState.Error -> {
                Text(text = (loginState as ResultState.Error).message, color = Color.Red)
            }
            is ResultState.Idle -> {
                // Estado Idle
            }
        }
    }
}

@Composable
fun ContentLoginView() {


}