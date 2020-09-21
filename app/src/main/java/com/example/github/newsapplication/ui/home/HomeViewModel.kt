package com.example.github.newsapplication.ui.home

import android.database.DatabaseUtils
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.github.newsapplication.base.safeLaunch
import com.example.github.newsapplication.entity.NetworkState
import com.example.github.newsapplication.entity.NewsData


class HomeViewModel(private val response : NewsListRepository) : ViewModel() {
    var netState = MutableLiveData<NetworkState>()
//    var cache: LiveData<PagedList<MyNewsData>>? = null
//    var articles: MutableLiveData<MutableList<NewsData>>? = null

    val articles = MutableLiveData<MutableList<NewsData>?>()
    var page = 1

    fun fetchHomeNews(empty:() ->Unit) {
        viewModelScope.safeLaunch {
            block = {
                netState.postValue(NetworkState.LOADING)
                articles.value = response.newsListArticles(page = 0)
                netState.postValue(NetworkState.LOADED)
            }
            onError = {
                netState.postValue(NetworkState.error(it.message))
            }
        }
    }
}


