package com.example.proyectofinal.presentation.screens.main.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.proyectofinal.model.entity.Menus
import com.example.proyectofinal.model.firebase.FirebaseManager

class MainViewModel (
    val context: Context
) : ViewModel() {
    val listaMenus = mutableStateListOf<Menus>()

    fun getMenusFirebase(){
        //Preguntar: Es la primera vez? -> SharedPreference (flag)
        //Si: Consulta a Firebase

        val sp = context.applicationContext.getSharedPreferences(
            "SP_INFO", Context.MODE_PRIVATE
        )

        val esPrimeraVez =
            sp.getBoolean("FLAG_ES_PRIMERA_VEZ", true)
        FirebaseManager.instance.getMenus(
            callbackSuccess = { menus ->
                menus.forEach{
                    listaMenus.add(it)
                }
            },
            callbackError = { error ->

            }
        )
    }
}