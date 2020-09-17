package com.example.github.newsapplication.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.recyclerview.widget.DiffUtil
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.*
import com.example.github.newsapplication.data.db.MyNewsData
import com.example.github.newsapplication.databinding.ItemNewsBinding
import com.example.github.newsapplication.entity.NetworkState
import com.example.github.newsapplication.entity.NewsData
import com.kuky.demo.wan.android.network.RetrofitManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *   Created by zhangziyi on 9/8/20 22:29
 */
class NewsListRepository {
    suspend fun newsListArticles(page: Int) : List<NewsData>? = withContext(Dispatchers.IO){
        RetrofitManager.apiService.homeArticles().datas
    }
}
class NewsArticleDataSource(
    private val repository : NewsListRepository
): PageKeyedDataSource<Int,NewsData>(), CoroutineScope by IOScope(){
    val initState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsData>
    ) {
        safeLaunch {
            block = {
                initState.postValue(NetworkState.LOADING)
                repository.newsListArticles(1)?.let {
                    callback.onResult(it, null, 1)
                    initState.postValue(NetworkState.LOADED)
                }
            }
            onError = {
                initState.postValue(NetworkState.error(it.message, ERROR_CODE_INIT))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsData>) {
       safeLaunch {
           block ={
               repository.newsListArticles(params.key)?.let {
                   callback.onResult(it, params.key + 1)
               }
           }
           onError ={
               initState.postValue(NetworkState.error(it.message, ERROR_CODE_MORE))
           }
       }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsData>) {

    }
}
class NewsListDataSourceFactory(
    private val repository: NewsListRepository
):DataSource.Factory<Int,NewsData>(){
    val source = MutableLiveData<NewsArticleDataSource>()

    override fun create(): DataSource<Int, NewsData> = NewsArticleDataSource(repository).apply {
        source.postValue(this)
    }
}

class HomeArticleAdapter : BasePagedListAdapter<MyNewsData, ItemNewsBinding>(DIFF_CALLBACK){


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyNewsData>() {
            override fun areItemsTheSame(oldItem: MyNewsData, newItem: MyNewsData): Boolean =
                oldItem.cache_id == newItem.cache_id

            override fun areContentsTheSame(oldItem: MyNewsData, newItem: MyNewsData): Boolean =
                oldItem == newItem
        }
    }

    override fun getLayoutId(layout: Int): Int = R.layout.item_news

    override fun setVariable(
        data: MyNewsData,
        position: Int,
        holder: BaseViewHolder<ItemNewsBinding>
    ) {
        holder.binding.data = data
    }
}

class NewsListCacheDiffCall(
    private val newList : MutableList<MyNewsData>?,
    private val oldList : MutableList<MyNewsData>? ):DiffUtil.Callback()
{
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (newList.isNullOrEmpty() || oldList.isNullOrEmpty()) false
        else newList[newItemPosition].cache_id == oldList[oldItemPosition].cache_id

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (newList.isNullOrEmpty() || oldList.isNullOrEmpty()) false
        else newList[newItemPosition] == oldList[oldItemPosition]

}


