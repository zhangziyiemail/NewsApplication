package com.example.github.newsapplication.ui.favorites

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseRecyclerAdapter
import com.example.github.newsapplication.base.BaseViewHolder
import com.example.github.newsapplication.data.MyDatabaseUtils
import com.example.github.newsapplication.databinding.ItemNewslistBinding
import com.example.github.newsapplication.entity.NewsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *   Created by zhangziyi on 9/22/20 21:27
 */

class FavoritesListRepository {
    suspend fun newsListArticles(): MutableList<NewsData>? =
        withContext(Dispatchers.IO) {
            MyDatabaseUtils.newsArticleCacheDao.loadAllArticles()
        }
}

class FavoritesAdapter :BaseRecyclerAdapter<NewsData>(null){



    override fun getLayoutId(layout: Int): Int = R.layout.item_newslist

    override fun setVariable(
        data: NewsData,
        position: Int,
        holder: BaseViewHolder<ViewDataBinding>
    ) {
        (holder.binding as ItemNewslistBinding).error= R.mipmap.error
        (holder.binding as ItemNewslistBinding).placeHolder= R.mipmap.picture
        (holder.binding as ItemNewslistBinding).newsdata = data
    }

    fun update(newData: MutableList<NewsData>?){
        val result = DiffUtil.calculateDiff(NewsListCacheDiffCall(newData, getAdapterData()), true)
        if (mData == null) {
            mData = mutableListOf()
        }
        mData?.addAll(newData ?: mutableListOf())
        result.dispatchUpdatesTo(this)
    }

}


class NewsListCacheDiffCall(
    private val newList : MutableList<NewsData>?,
    private val oldList : MutableList<NewsData>? ):DiffUtil.Callback()
{
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (newList.isNullOrEmpty() || oldList.isNullOrEmpty()) false
        else newList[newItemPosition].title == oldList[oldItemPosition].title

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (newList.isNullOrEmpty() || oldList.isNullOrEmpty()) false
        else newList[newItemPosition] == oldList[oldItemPosition]

}
