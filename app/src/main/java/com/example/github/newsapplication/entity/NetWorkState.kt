package com.example.github.newsapplication.entity

import com.example.github.newsapplication.base.ERROR_CODE_NORM

/**
 *   Created by zhangziyi on 9/8/20 23:04
 */
enum class State {
    RUNNING, SUCCESS, FAILED
}

data class NetworkState(
    val status: State,
    val totalResults: String? = null,
    val code: Int? = null
) {
    companion object {
        val LOADED = NetworkState(State.SUCCESS)
        val LOADING = NetworkState(State.RUNNING)
        fun error(msg: String?, code: Int = ERROR_CODE_NORM) = NetworkState(State.FAILED, msg ?: "unknown error", code)
    }
}