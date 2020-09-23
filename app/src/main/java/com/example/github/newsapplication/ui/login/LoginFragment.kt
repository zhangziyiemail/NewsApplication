package com.example.github.newsapplication.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.R
import com.example.github.newsapplication.Utils.PreferencesHelper
import com.example.github.newsapplication.Utils.SharePreferencesUtils
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentLoginBinding
import com.example.github.newsapplication.entity.ObservableUser
import kotlinx.android.synthetic.main.fragment_login.*

/**
 *   Created by zhangziyi on 9/21/20 04:04
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {


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
                    R.id.tv_forget -> mNavController.navigate(R.id.action_loginFragment_to_forgetFragment)
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
            ObservableUser.image.set(SharePreferencesUtils.getString(requireContext(),"image"))
            ObservableUser.accout.set(SharePreferencesUtils.getString(requireContext(),"account"))
            ObservableUser.password.set(SharePreferencesUtils.getString(requireContext(),"password"))
            mNavController.popBackStack()
        }else{
            Toast.makeText(context,"account or password is error",Toast.LENGTH_SHORT).show()
        }

    }
    fun toRegisterFragment(){
        mNavController.navigate(R.id.action_loginFragment_to_registerFragment)
    }
}