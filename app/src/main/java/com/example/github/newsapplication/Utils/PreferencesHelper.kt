package com.example.github.newsapplication.Utils

import android.content.Context

/**
 *   Created by zhangziyi on 9/21/20 14:30
 */
object PreferencesHelper {
    private const val STATE_KEY_FIRST_INT = "wan.state.first.in"
    fun saveFirstState(context: Context, isFirst: Boolean) =
        SharePreferencesUtils.saveBoolean(context, STATE_KEY_FIRST_INT, isFirst)

    fun isFirstIn(context: Context) =
        SharePreferencesUtils.getBoolean(context, STATE_KEY_FIRST_INT, true)
}