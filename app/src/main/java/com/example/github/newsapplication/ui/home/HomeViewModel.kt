package com.example.github.newsapplication.ui.home

import android.database.DatabaseUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.github.newsapplication.data.MyDataBase
import com.example.github.newsapplication.data.MyDatabaseUtils
import com.example.github.newsapplication.data.db.MyNewsData
import com.example.github.newsapplication.entity.NetworkState


class HomeViewModel(private val response : NewsListRepository) : ViewModel() {
    var netState :LiveData<NetworkState>? = null
    var cache: LiveData<PagedList<MyNewsData>>? = null
    var articles: LiveData<PagedList<MyNewsData>>? = null


    fun fetchHomeNews(empty:() ->Unit){

    }

    fun  fetchCache(empty: () -> Unit){
        cache = LivePagedListBuilder(
            MyDatabaseUtils.newsArticleCacheDao.fetchAllCache(),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .build()
        ).setBoundaryCallback(object : PagedList.BoundaryCallback<MyNewsData>() {
            override fun onZeroItemsLoaded() = empty()
        }).build()
    }

}


