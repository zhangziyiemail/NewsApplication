package com.example.github.newsapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *   Created by zhangziyi on 9/14/20 18:38
 */
 class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Context
    }
}