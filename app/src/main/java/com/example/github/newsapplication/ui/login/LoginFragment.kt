package com.example.github.newsapplication.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.R
import com.example.github.newsapplication.Utils.PreferencesHelper
import com.example.github.newsapplication.Utils.SharePreferencesUtils
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

/**
 *   Created by zhangziyi on 9/21/20 04:04
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    fun login(view :View){

    }

    fun register(view :View){
        mNavController.navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun forget(view: View){

    }

    override fun getLayoutId(): Int = R.layout.fragment_login
    override fun actionsOnViewInflate() {
    }
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.let {
            binding->
            binding.viewclick = View.OnClickListener {
               view->
                when(view.id){
                    R.id.but_login -> loginToHomeFragment(account.text.toString(),password.text.toString())
                    R.id.but_regist -> toRegisterFragment()
                }
            }
        }
    }

    fun loginToHomeFragment(account :String, password : String){

        if ( account.isEmpty() ){
            Toast.makeText(context,"input account please",Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty() ){
            Toast.makeText(context,"input password please",Toast.LENGTH_SHORT).show()
            return
        }

        if (SharePreferencesUtils.getString(MyApplication.instance,account).equals(password)){
            //todo  跳转首页 fragment  menu 刷新数据
        }else{
            Toast.makeText(context,"account or password is error",Toast.LENGTH_SHORT).show()
        }

    }
    fun toRegisterFragment(){
        mNavController.navigate(R.id.action_loginFragment_to_registerFragment)
    }
}