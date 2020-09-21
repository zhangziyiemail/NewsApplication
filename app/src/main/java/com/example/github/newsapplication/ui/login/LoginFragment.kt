package com.example.github.newsapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentLoginBinding

/**
 *   Created by zhangziyi on 9/21/20 04:04
 */
class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}