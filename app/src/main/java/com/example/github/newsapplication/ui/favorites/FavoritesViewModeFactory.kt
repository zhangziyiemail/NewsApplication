package com.example.github.newsapplication.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.github.newsapplication.ui.home.NewsListRepository

/**
 *   Created by zhangziyi on 9/22/20 21:27
 */
class FavoritesViewModeFactory(private val repository: FavoritesListRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(repository) as T
    }
}