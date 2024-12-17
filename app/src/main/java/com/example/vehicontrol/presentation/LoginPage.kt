package com.example.vehicontrol.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vehicontrol.presentation.viewmodel.LoginViewModel
import com.example.vehicontrol.presentation.viewmodel.SuiviVehiculeViewModel
import com.example.vehicontrol.ui.theme.lexendFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage (
    onNavigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()

){
    val errorMessage by remember { loginViewModel.errorMessage }
    //val response by remember { loginViewModel.loginResponse }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isLoggedIn by loginViewModel.isLoggedIn

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            onNavigateToHomeScreen()
        }
    }

    if (errorMessage != null) {
        Text(text = errorMessage ?: "Une erreur est survenue")
    }

  /*  LaunchedEffect(response) {
        response?.let {
            if (it.isSuccessful) {
                onNavigateToHomeScreen()
            } else {
                Log.e("LoginPage", "Erreur de connexion: ${errorMessage ?: "Inconnu"}")
            }
        }
    }

   */


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Padding around the content
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // E-mail TextField
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail", style = TextStyle(fontFamily = lexendFont) ) },
            placeholder = { Text("Entrez votre adresse e-mail", style = TextStyle(fontFamily = lexendFont)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent// Customizing the background color
            )
        )

        Spacer(modifier = Modifier.height(16.dp)) // Spacer between fields

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe" ,style = TextStyle(fontFamily = lexendFont) )  },
            placeholder = { Text("Entrez votre mot de passe", style = TextStyle(fontFamily = lexendFont)) },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                // Toggle password visibility icon
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent // Customizing the background color
            )
        )

        Spacer(modifier = Modifier.height(24.dp)) // Spacer before the button

        Button(onClick = {
            // Log pour vérifier que l'action se déclenche
            Log.d("LoginScreen", "Enregistrement des données...")

            loginViewModel.submitLogin(
                email,
                password
            )
        },
                modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(24.dp))



    }
}