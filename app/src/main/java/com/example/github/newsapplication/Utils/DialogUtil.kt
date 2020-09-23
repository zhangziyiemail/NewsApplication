package com.example.github.newsapplication.Utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 *   Created by zhangziyi on 9/22/20 23:52
 */

class DialogUtil: DialogFragment() {

    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogItemClick(which: Int)
    }

   open fun setListener(listener: NoticeDialogListener){
       this.listener = listener
   }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setItems(arrayOf("camera","photo"),DialogInterface.OnClickListener { dialog, which ->
                listener.onDialogItemClick(which)
            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}