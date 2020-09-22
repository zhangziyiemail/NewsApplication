package com.example.github.newsapplication.ui.favorites

import android.os.Bundle
import android.view.View
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentFavoritesBinding
import com.example.github.newsapplication.ui.home.HomeArticleAdapter

/**
 *   Created by zhangziyi on 9/21/20 23:13
 */
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    private val mAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    override fun getLayoutId(): Int  = R.layout.fragment_favorites

    override fun actionsOnViewInflate() {

    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

    }
}