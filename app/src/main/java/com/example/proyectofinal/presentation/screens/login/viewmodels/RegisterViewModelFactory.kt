package com.example.proyectofinal.presentation.screens.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegisterViewModelFactory (
    private val onRegistroSuccess : (String) -> Unit
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(onRegistroSuccess) as T
    }
}