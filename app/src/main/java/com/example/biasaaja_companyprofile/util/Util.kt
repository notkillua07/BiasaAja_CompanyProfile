package com.example.studentproject.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.biasaaja_companyprofile.model.CompanyProfileDatabase

fun createNotificationChannel(
    context: Context,
    importance: Int,
    showBadge: Boolean,
    name: String,
    description: String
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager =
            context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

// Database names
const val DB_NAME = "companyprofile"


fun buildCompanyProfileDb(context: Context): CompanyProfileDatabase {
    return CompanyProfileDatabase.buildDatabase(context)
}
