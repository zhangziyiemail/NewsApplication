package com.example.github.newsapplication.Utils

import android.content.Context

/**
 *   Created by zhangziyi on 9/21/20 14:30
 */
object PreferencesHelper {
    private const val STATE_KEY_FIRST_INT = "news.state.first.in"
    private const val USER_KEY_ID = "news.user.id"

    fun saveFirstState(context: Context, isFirst: Boolean) =
        SharePreferencesUtils.saveBoolean(context, STATE_KEY_FIRST_INT, isFirst)

    fun isFirstIn(context: Context) =
        SharePreferencesUtils.getBoolean(context, STATE_KEY_FIRST_INT, true)

    fun hasLogin(context: Context) =
        SharePreferencesUtils.getInteger(context, USER_KEY_ID) > 0

    fun saveUserId(context: Context, id: Int) =
        SharePreferencesUtils.saveInteger(context, USER_KEY_ID, id)
}