package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectofinal.presentation.screens.login.LoginNavigation

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Consultar en shared preferences
        val username = obtenerCredencialesLogin()
        if (username != null && username != ""){
            //No es la primera vez (ya me loguie antes)
            pasarAlMain()
        }else{
            setContent {
                LoginNavigation(
                    onLoginSuccess = guardarCredencialesLogin
                )
            }
        }

    }

    val guardarCredencialesLogin : (String) -> Unit = {username : String ->
        //Guardando en shared preferences
        val sp = getSharedPreferences("SP_CREDENCIALES", MODE_PRIVATE)
        sp.edit().putString("USERNAME", username).commit()

        pasarAlMain()
    }

    private fun pasarAlMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    val obtenerCredencialesLogin : () -> String? = {
        val sp = getSharedPreferences("SP_CREDENCIALES", MODE_PRIVATE)
        val username = sp.getString("USERNAME","")
        username
    }
}