package com.example.github.newsapplication.widget

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle


/**
 *   Created by zhangziyi on 9/24/20 15:16
 */

class ReciveBroadcastActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Test")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: AlertDialog = builder.create()
        alert.show()

    }

}