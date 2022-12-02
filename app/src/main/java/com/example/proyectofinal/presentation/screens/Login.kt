package com.example.proyectofinal.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.proyectofinal.presentation.screens.login.viewmodels.LoginViewModel
import com.example.proyectofinal.presentation.screens.login.viewmodels.LoginViewModelFactory

@Composable
fun Login(
    vm : LoginViewModel = viewModel(
        factory = LoginViewModelFactory()
    ),
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = vm.username.value,
            onValueChange = {
                vm.username.value = it
            }
        )
        OutlinedTextField(
            value = vm.password.value,
            onValueChange = {
                vm.password.value = it
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Text(
            text = vm.error.value
        )
        Button(
            onClick = {
                vm.loginFirebase()
                navController.navigate("principal")
            }
        ) {
            Text(text = "Login")
        }
    }
}