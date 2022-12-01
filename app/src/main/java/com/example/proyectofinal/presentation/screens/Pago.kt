package com.example.proyectofinal.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Pago(
    navController: NavController,
    onNotificationClick:()-> Unit

){
    Spacer(modifier= Modifier.width(100.dp))
    Row(
        modifier = Modifier.fillMaxWidth().padding(70.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text("Aqui va el pago")
    }
    Spacer(
        modifier = Modifier.width(20.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth().padding(80.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = { navController.navigate("confirmacion") }
        ) {
            Text("Siguiente")
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {onNotificationClick()}) {
            Text(text = "Enviar Compra")

        }
    }

}