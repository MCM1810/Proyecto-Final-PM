package com.example.proyectofinal.presentation.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.proyectofinal.model.entity.Menus

@Composable
fun ListaMenus(
    menus :  List<Menus>,
) {
    LazyColumn {
        items(menus) { menu ->
            Row() {
                Text(text = "${menu.nombre}")
            }
        }
    }
}