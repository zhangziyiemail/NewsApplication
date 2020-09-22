package com.example.github.newsapplication

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.github.newsapplication.Utils.PreferencesHelper
import com.example.github.newsapplication.base.BaseActivity
import com.example.github.newsapplication.databinding.ActivityMainBinding
import com.example.github.newsapplication.ui.home.HomeFragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var availableCount = 0
    val hasLogin = MutableLiveData<Boolean>()

    private val manager: ConnectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val request: NetworkRequest by lazy {
        NetworkRequest.Builder().build()
    }

    private val netStateCallback: ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                availableCount++
                checkState()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                availableCount--
                checkState()
            }
        }
    }

    private fun checkState() {
        mBinding.netAvailable = availableCount > 0
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.unregisterNetworkCallback(netStateCallback)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initActivity(savedInstanceState: Bundle?) {
        manager.registerNetworkCallback(request, netStateCallback)
        if (PreferencesHelper.isFirstIn(this)) {
            alert(
                resources.getString(R.string.operate_helper)
            ) {
                isCancelable = false
                yesButton { PreferencesHelper.saveFirstState(this@MainActivity, false) }
            }.show()
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.first()
            .childFragmentManager.fragments.last().let {
                if (it is HomeFragment) {
                    startActivity(
                        Intent(Intent.ACTION_MAIN)
                            .apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                addCategory(Intent.CATEGORY_HOME)
                            })
                    return
                }
            }

        super.onBackPressed()
    }

}