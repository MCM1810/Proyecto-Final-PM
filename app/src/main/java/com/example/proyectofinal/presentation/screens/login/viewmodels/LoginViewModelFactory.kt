package com.example.proyectofinal.presentation.screens.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory (
    private val onLoginSuccess : (String) -> Unit,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(onLoginSuccess) as T
    }
}