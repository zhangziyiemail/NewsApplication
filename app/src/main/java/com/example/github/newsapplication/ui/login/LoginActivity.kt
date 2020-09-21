package com.example.github.newsapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

/**
 *   Created by zhangziyi on 9/21/20 03:18
 */
class LoginActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (R.id.fragment_container != null) {
            if (savedInstanceState != null) {
                return;
            }
            val firstFragment = LoginFragment()
            firstFragment.arguments = intent.extras
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit()
        }
    }

    fun regist(v: View?){
       supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container,RegistFragment(), null)
            ?.addToBackStack(null)
            ?.commit();

    }

    fun upImage(view: View){
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    fun login(v: View){
        account
    }
}