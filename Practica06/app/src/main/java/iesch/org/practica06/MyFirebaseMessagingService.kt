package iesch.org.practica06

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_TOKEN", "Nuevo token: $token")
        // Aquí podrías guardarlo en DataStore o mandarlo a Firestore
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val titulo = message.notification?.title ?: "Título"
        val cuerpo = message.notification?.body ?: "Mensaje"

        val channelId = "default_channel"

        val notificationManager =
            getSystemService(android.content.Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                channelId,
                "Notificaciones",
                android.app.NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val builder = androidx.core.app.NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(cuerpo)
            .setAutoCancel(true)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }


}