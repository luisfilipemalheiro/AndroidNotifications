package com.example.not

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private fun createNotification() {
        val channelId = "my_channel_id"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ola)
            .setContentTitle("My Notification")
            .setContentText("This is my notification")
            .setPriority(NotificationCompat.PRIORITY_MAX) // or PRIORITY_MAX
            .setVibrate(longArrayOf(0, 1000, 500, 1000)) // vibration pattern
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // notification sound


        // Create a notification manager
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the notification channel (if necessary)
        createNotificationChannel(channelId, notificationManager)

        // Show the notification
        notificationManager.notify(0, notificationBuilder.build())

        println("cheguei aqui")
    }

    private fun createNotificationChannel(channelId: String, notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "My Channel Name"
            val channelDescription = "My Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button in the layout file
        val button = findViewById<Button>(R.id.notification_button)

        // Set a click listener on the button
        button.setOnClickListener {
            createNotification()
        }
    }
}
