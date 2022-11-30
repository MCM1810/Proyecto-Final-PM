package com.example.proyectofinal.presentation.screens.login.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.model.firebase.FirebaseManager

class LoginViewModel (
    private val onLoginSuccess : (String) -> Unit,
) : ViewModel() {
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var error = mutableStateOf("")

    fun loginFirebase(){
        FirebaseManager.instance.login(
            username = username.value,
            password = password.value,
            onError = { msg ->
                //Login incorrecto
                println("ERROR")
                error.value = msg
            }
        ) { name ->
            //Login correcto
            onLoginSuccess(name)
        }
    }
}