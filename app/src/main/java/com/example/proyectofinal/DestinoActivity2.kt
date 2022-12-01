package com.example.proyectofinal

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.namespace.R
import com.example.proyectofinal.presentation.screens.Confirmacion
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
val CHANNEL_ID2="1"
class DestinoActivity2 : ComponentActivity() {
    private   var  channel: NotificationChannel?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        createNotificationChannel()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "confirmacion" ){
                /*composable("login") {
                    Login(navController)
                }

                composable("principal"){
                    PantallaPrincipal(navController)
                }
                composable("pago"){
                    Pago(navController, onNotificationClick = sendNotifiaction)
                }*/
                composable("confirmacion"){
                    Confirmacion(navController,onNotificationClick2 = sendNotifiaction2)
                }
            }



        }

    }
    val sendNotifiaction2= {
        val notif2=createNotification2()
        val notifManager2= NotificationManagerCompat.from(this)
        notifManager2.notify(1,notif2)
    }

    fun createNotification2(): Notification {
        val notif= NotificationCompat.Builder(this, CHANNEL_ID2)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentInfo("Envio de Notifiación")
            .setContentText("Se envio su notificación al recepcionista")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        return notif
    }


    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            channel= NotificationChannel(
                CHANNEL_ID,
                "Canal de notifiacion 1",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description="Es una descripcion"
            }
            //Registramos el canal del sistema
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel!!)


        }
    }







}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ProyectoFinalTheme {
        Greeting2("Android")
    }
}