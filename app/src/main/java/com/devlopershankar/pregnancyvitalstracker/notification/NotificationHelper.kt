package com.devlopershankar.pregnancyvitalstracker.notification


import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.devlopershankar.pregnancyvitalstracker.R

object NotificationHelper {
    fun sendNotification(context: Context) {
        val builder = NotificationCompat.Builder(context, "vital_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Time to log your vitals!")
            .setContentText("Stay on top of your health. Please update your vitals now!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}
