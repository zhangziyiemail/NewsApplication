package com.example.github.newsapplication

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.example.github.newsapplication.widget.ReciveBroadcastActivity

/**
 *   Created by zhangziyi on 9/24/20 13:55
 */

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {
    var notificationId = 1

    var notificationCallbacks = "com.example.broadcast.MY_NOTIFICATION_CALLBSCK"


    val snoozePendingIntent: PendingIntent =
        PendingIntent.getBroadcast(MyApplication.instance, 0, snoozeIntent(), 0)

    override fun onReceive(context: Context, intent: Intent) {
        with(NotificationManagerCompat.from(MyApplication.instance)) {
            Log.v("action++", intent.action)
            when (intent.getStringExtra("type")) {
                "notfied" -> StringBuilder().apply {
                    var builder = NotificationCompat.Builder(MyApplication.instance, TAG)
                        .setSmallIcon(R.drawable.ic_menu_camera)
                        .setContentTitle(intent.getStringExtra("title"))
                        .setContentText(intent.getStringExtra("data"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(resultIntent())
                        .addAction(
                            R.drawable.ic_menu_camera, "click see detail",
                            snoozePendingIntent
                        ).build()

                    notify(notificationId, builder)
                }
                "callback" -> {
                    MyApplication.instance.startActivity(Intent(MyApplication.instance,ReciveBroadcastActivity::class.java))
//                    val type = AlertDialog.Builder(MyApplication.instance)
//                        .setMessage(intent.getStringExtra("data"))
//                        .setTitle(intent.getStringExtra("title")).create()
//                    type.window?.setType(WindowManager.LayoutParams.TYPE_TOAST)
//                    type.show()
                }
                else -> {
                }
            }

        }

    }

    fun resultIntent(): PendingIntent? {
        val resultIntent = Intent(MyApplication.instance, ReciveBroadcastActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? =
            TaskStackBuilder.create(MyApplication.instance).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(resultIntent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }
        return resultPendingIntent
    }

    fun snoozeIntent(): Intent {
        val intent = Intent(MyApplication.instance, MyBroadcastReceiver::class.java)
        intent.setAction(notificationCallbacks)
        intent.putExtra("type", "callback")
        intent.putExtra("data", "click something")
        intent.putExtra("title", "callback")
        return intent
    }

}


