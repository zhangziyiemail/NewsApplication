package com.example.github.newsapplication.ui.detail

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.NavController
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentDetailBinding
import com.example.github.newsapplication.entity.NewsData

/**
 *   Created by zhangziyi on 9/21/20 05:57
 */
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val title : String? by lazy {
        arguments?.getString("title")
    }
    private val url : String? by lazy {
        arguments?.getString("url")
    }
    private val content : String? by lazy {
        arguments?.getString("content")
    }
    override fun getLayoutId(): Int=R.layout.fragment_detail

    override fun actionsOnViewInflate() {

    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.title = title ?: ""
        mBinding?.url = url ?: ""
        mBinding?.content = content ?: ""
        mBinding?.error =R.mipmap.error
        mBinding?.placeHolder = R.mipmap.picture
    }


    companion object {
        fun viewDetail(controller: NavController, @IdRes id: Int, title : String?, url : String?, content : String?) {
            controller.navigate(id, Bundle().apply {
                putString("title",title)
                putString("url",url)
                putString("content",content)
            })
        }
    }
}