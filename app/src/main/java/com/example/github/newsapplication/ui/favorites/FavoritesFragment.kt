package com.example.github.newsapplication.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.base.OnItemClickListener
import com.example.github.newsapplication.base.OnItemLongClickListener
import com.example.github.newsapplication.data.MyDatabaseUtils
import com.example.github.newsapplication.databinding.FragmentFavoritesBinding
import com.example.github.newsapplication.entity.NewsData
import com.example.github.newsapplication.ui.detail.DetailFragment
import com.example.github.newsapplication.ui.home.HomeArticleAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

/**
 *   Created by zhangziyi on 9/21/20 23:13
 */
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    private val mAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity(), FavoritesViewModeFactory(FavoritesListRepository()))
            .get(FavoritesViewModel::class.java)
    }

    override fun getLayoutId(): Int  = R.layout.fragment_favorites

    override fun actionsOnViewInflate() {
        mViewModel.fetchHomeNews{}
        mViewModel.articles?.observe(this, Observer {
            mAdapter.update(it)
        })
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.let {
            binding->
            binding.adapter = mAdapter

            binding.itemLongClick =
                OnItemLongClickListener { position, _ ->
                    mAdapter.getItemData(position)?.let { article ->
                        showCollectDialog(article)
                    }
                }

            binding.itemClick =
                OnItemClickListener { position, v ->
                    mAdapter.getItemData(position)?.let { art ->
                        Log.v("detail",art.title+"+"+art.urlToImage+"+"+art.content)
                        DetailFragment.viewDetail(
                            mNavController,
                            R.id.action_favoritesFragment_to_detailFragment,
                            art.title,
                            art.urlToImage,
                            art.content
                        )
                    }
                }
        }
    }

    private fun showCollectDialog(article: NewsData) {
        var collect: Boolean = false
        GlobalScope.launch(Dispatchers.IO) {
            collect =
                article.title?.let { MyDatabaseUtils.newsArticleCacheDao.findArticle(it) } == null
        }
        requireContext().alert(
            if (collect) "「${article.title}」collected"
            else "collect 「${article.title}」"
        ) {
            yesButton {
                GlobalScope.launch(Dispatchers.IO) {
                    MyDatabaseUtils.newsArticleCacheDao.cacheHomeArticles(article)
                }

            }
            noButton {

            }
        }.show()
    }
}