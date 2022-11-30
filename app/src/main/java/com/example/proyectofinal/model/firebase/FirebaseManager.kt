package com.example.proyectofinal.model.firebase

import android.util.Log
import com.example.proyectofinal.model.entity.Menus
import com.example.proyectofinal.model.firebase.FirebaseManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseManager {
    val db : FirebaseFirestore

    init {
        db = Firebase.firestore
    }

    companion object {
        private var _instance : FirebaseManager? = null
        val instance : FirebaseManager
            get() {
                if (_instance == null) {
                    _instance = FirebaseManager()
                }
                return _instance!!
            }
    }

    fun getMenus(
        callbackSuccess : (List<Menus>) -> Unit,
        callbackError : (String) -> Unit
    ) {
        db.collection("Menus")
            .get()
            .addOnSuccessListener { documents ->
                val lista = mutableListOf<Menus>()
                documents.forEach{ doc->
                    lista.add(
                        Menus(
                            id = doc.id,
                            nombre = doc.data["nombre"]!! as String,
                            contenido = doc.data["contenido"]!! as String,
                            descripcion = doc.data["descripcion"]!! as String,
                            precio = doc.data["precio"]!! as Int
                        )
                    )
                }
                callbackSuccess(lista)
            }.addOnFailureListener{ exception->
                Log.e("Firebase Error", exception.message!!)
                callbackError(exception.message!!)
            }
    }

    fun login(
        username : String,
        password : String,
        onError : (String) -> Unit,
        onSuccess : (String) -> Unit
    ){
        db.collection("usuarios")
            .whereEqualTo("Username", username)
            .whereEqualTo("Password", password)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.size() > 0){
                    val name = snapshot.documents[0].data!!
                        .get("Nombre")!!.toString()
                    onSuccess(name)
                }else{
                    //error en el login
                    onError("Login incorrecto")
                }
            }.addOnFailureListener {
                onError(it.message.toString())
            }
    }

    fun registrarUsuario(
        nombre : String,
        username : String,
        password : String,
        rol : String,
        onError : (String) -> Unit,
        onSuccess : (String) -> Unit
    ){
        val newDoc = db.collection("usuarios").document()
        val data = HashMap<String, Any>()
        data["Nombre"] = nombre
        data["Username"] = username
        data["Password"] = password
        data["Rol"] = rol

        newDoc.set(data)
            .addOnSuccessListener {
                onSuccess(nombre)
            }.addOnFailureListener{ exception ->
                onError(exception.message!!.toString())
            }
    }
}