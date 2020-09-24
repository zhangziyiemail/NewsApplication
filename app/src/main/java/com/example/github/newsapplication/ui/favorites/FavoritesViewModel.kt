package com.example.github.newsapplication.ui.favorites

import android.database.DatabaseUtils
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.Utils.PreferencesHelper
import com.example.github.newsapplication.Utils.SharePreferencesUtils
import com.example.github.newsapplication.base.IOScope
import com.example.github.newsapplication.base.safeLaunch
import com.example.github.newsapplication.data.MyDatabaseUtils
import com.example.github.newsapplication.entity.NetworkState
import com.example.github.newsapplication.entity.NewWorkState
import com.example.github.newsapplication.entity.NewsData
import com.example.github.newsapplication.ui.home.NewsListRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope


class FavoritesViewModel(private val response : FavoritesListRepository) : ViewModel(){
    val articles = MutableLiveData<MutableList<NewsData>?>()
    //吧数据变成本地的实体类 返回给 fragment
    fun fetchHomeNews(empty:() ->Unit) {
        viewModelScope.safeLaunch {
            block = {
                articles.value = response.newsListArticles()
            }
        }
    }
}


