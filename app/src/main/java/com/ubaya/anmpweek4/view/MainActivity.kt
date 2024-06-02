package com.ubaya.anmpweek4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.ubaya.anmpweek4.R
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.ubaya.anmpweek4.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }
    companion object {
        private var instance:MainActivity ?= null
        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_HIGH
                setAutoCancel(true)
            }

            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)

            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS),101)
                return
            }
            notificationManager.notify(1001, notificationBuilder.build())
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1 -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("permission", "granted")
                    createNotificationChannel(this,
                        NotificationManagerCompat.IMPORTANCE_HIGH, false,
                        getString(R.string.app_name), "App notification channel.")

                } else {
                    Log.d("permission", "deny")
                }

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, getString(R.string.app_name), "App notification channel.")
    }
}