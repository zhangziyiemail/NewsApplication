package com.example.github.newsapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.R
import com.example.github.newsapplication.Utils.SharePreferencesUtils
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentForgetBinding
import kotlinx.android.synthetic.main.fragment_regist.*

/**
 *   Created by zhangziyi on 9/22/20 23:22
 */

class ForgetFragment :BaseFragment<FragmentForgetBinding>(){

    override fun getLayoutId(): Int = R.layout.fragment_forget

    override fun actionsOnViewInflate() {

    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
            mBinding?.let {
                binding->
                binding.viewclick = View.OnClickListener {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "*/*"
                        putExtra(Intent.EXTRA_EMAIL, account.text.toString())
                        putExtra(Intent.EXTRA_SUBJECT, SharePreferencesUtils.getString(requireContext(),"account"))

                    }
                    if (intent.resolveActivity(requireContext().packageManager) != null) {
                        startActivity(intent)
                    }
                }
            }
    }





}