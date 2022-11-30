package com.example.proyectofinal.presentation.screens.login.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.model.firebase.FirebaseManager

class RegisterViewModel (
    private val onRegistroSuccess : (String) -> Unit
) : ViewModel() {
    val name = mutableStateOf("")
    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val rol = mutableStateOf("")

    fun registrarFirebase(){
        FirebaseManager.instance.registrarUsuario(
            nombre = name.value,
            username = username.value,
            password = password.value,
            rol = rol.value,
            onError = { msg ->
                Log.e("ERROR", msg)
            }
        ) {
            onRegistroSuccess(name.value)
        }
    }
}