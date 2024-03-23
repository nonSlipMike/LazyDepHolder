package com.example.myapp.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.common.config.APP_CHANNEL_NOTIFY

fun App.createNotificationChannel() {
	// Create the NotificationChannel, but only on API 26+ because
	// the NotificationChannel class is new and not in the support library
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		val name = APP_CHANNEL_NOTIFY
		val descriptionText = APP_CHANNEL_NOTIFY
		val importance = NotificationManager.IMPORTANCE_DEFAULT
		val channel = NotificationChannel(APP_CHANNEL_NOTIFY, name, importance).apply {
			description = descriptionText
		}
		// Register the channel with the system
		val notificationManager: NotificationManager =
			getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
		notificationManager.createNotificationChannel(channel)
	}
}