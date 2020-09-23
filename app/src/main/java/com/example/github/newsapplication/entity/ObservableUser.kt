package com.example.github.newsapplication.entity

import androidx.databinding.ObservableField

/**
 *   Created by zhangziyi on 9/23/20 02:59
 */
object ObservableUser {
    var accout = ObservableField<String>()
    var password = ObservableField<String>()
    var image =  ObservableField<String>()
    var error =  "please login"
}