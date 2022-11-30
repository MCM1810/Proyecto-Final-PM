package com.example.proyectofinal.model.entity

import java.sql.Timestamp

data class Pedidos (
    val id : String,
    val fecha_pedido : Timestamp,
    val idProducto : String,
    val idUsuario : String
)