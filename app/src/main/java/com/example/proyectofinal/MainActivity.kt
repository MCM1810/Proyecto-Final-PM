package com.example.proyectofinal

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.namespace.R
import com.example.proyectofinal.presentation.screens.Confirmacion
import com.example.proyectofinal.presentation.screens.Pago
import com.example.proyectofinal.presentation.screens.PantallaPrincipal
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme

val CHANNEL_ID="1"
class MainActivity : ComponentActivity() {
    private var channel: NotificationChannel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        setContent {

            ProyectoFinalTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "principal" ){
                    composable("principal"){
                        PantallaPrincipal(navController)
                    }
                    composable("pago"){
                        Pago(navController, onNotificationClick = sendNotifiaction)
                    }
                    composable("confirmacion"){
                        Confirmacion(navController,onNotificationClick2 = sendNotifiaction2)
                    }
                }
            }

        }

    }
    val sendNotifiaction2={

    }
    val sendNotifiaction= {
        val notif=createNotification()
        val notifManager= NotificationManagerCompat.from(this)
        notifManager.notify(1,notif)
    }

    fun createNotification(): Notification {
        val intent= Intent(this,DestinoActivity2::class.java).apply {
            flags= Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
        }


        val pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_MUTABLE)

        val notif= NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentInfo("Notifiación de pedido")
            .setContentText("Su pedido se registro con exito")
            .setContentIntent(pendingIntent)
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