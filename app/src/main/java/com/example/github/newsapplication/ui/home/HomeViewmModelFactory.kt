package com.example.github.newsapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *   Created by zhangziyi on 9/12/20 20:02
 */
class HomeViewmModelFactory(private val repository: NewsListRepository):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}