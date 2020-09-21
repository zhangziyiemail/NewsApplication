package com.example.github.newsapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentDetailBinding

/**
 *   Created by zhangziyi on 9/21/20 05:57
 */
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun getLayoutId(): Int=R.layout.fragment_detail

    override fun actionsOnViewInflate() {

    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

    }


}