package com.example.proyectofinal.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal.presentation.components.DrawerBody
import com.example.proyectofinal.presentation.components.DrawerHeader
import com.example.proyectofinal.presentation.components.MenuItem
import com.example.proyectofinal.presentation.components.AppBar
import kotlinx.coroutines.launch


@Composable
fun PantallaPrincipal(
    navController: NavController
){



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }

                    }
                )
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                DrawerHeader()
                DrawerBody(items = listOf(
                    MenuItem(
                        id = "login",
                        title = "Cerrar Sesion",
                        contentDescription = "Cerrar Sesion",
                        icon = Icons.Default.Home
                    )

                ),
                    onItemClick = {


                        println("Clicked on ${it.title}")
                        if (it.title == "Cerrar Sesion"){
                            navController.navigate("login")
                        }
                    } )
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(70.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text("Aqui va el la pantalla principal")
            }
            Spacer(
                modifier = Modifier.width(20.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = { navController.navigate("pago") }
                ) {
                    Text("Pagar")
                }
            }
        }
    }
}