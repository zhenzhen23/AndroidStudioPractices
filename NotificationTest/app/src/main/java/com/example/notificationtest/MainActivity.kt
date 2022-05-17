package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel2 = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel2)
        }

        sendNotice.setOnClickListener {
            val intent = Intent(this,NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0 ,intent, PendingIntent.FLAG_MUTABLE)
            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            manager.notify(1, notification)
        }
    }
}