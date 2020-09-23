package com.example.github.newsapplication.ui.login

import android.os.Bundle
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

    override fun getLayoutId(): Int = R.layout.fragment_regist

    override fun actionsOnViewInflate() {

    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

    }

    fun submit(account: String, password: String) {
        if (SharePreferencesUtils.getString(MyApplication.instance,account).isEmpty()){
            SharePreferencesUtils.saveString(MyApplication.instance,account,password)
            mNavController.popBackStack()
        }
    }



}