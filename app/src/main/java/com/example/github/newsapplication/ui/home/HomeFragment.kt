package com.example.github.newsapplication.ui.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.base.ERROR_CODE_INIT
import com.example.github.newsapplication.databinding.FragmentHomeBinding
import com.example.github.newsapplication.entity.State
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val mAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity(),HomeViewmModelFactory(NewsListRepository()))
            .get(HomeViewModel::class.java)
    }

    private val mCollectionViewModel by lazy{

    }

    private var isFirstObserver = true
    private var hasCache = true
    private var onInflated = true

    override fun actionsOnViewInflate() {
        mViewModel.fetchCache {
            hasCache = false
        }

        fetchHomeArticleList()

        mViewModel.cache?.observe(this, Observer {
            if (onInflated){
                onInflated = false
                mAdapter.submitList(it)
            }
        })




    }
    override fun getLayoutId()= R.layout.fragment_home

    @SuppressLint("ClickableViewAccessibility")
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.let {
            it.refreshColor = R.color.colorAccent
        }
    }

    fun  fetchHomeArticleList(){
        mViewModel.fetchHomeNews {
            mBinding?.emptyStatus = !hasCache
        }
        mViewModel.netState?.observe(this, Observer{
            when(it.status){
                State.RUNNING -> injectStates(true,loading= !hasCache)
                State.SUCCESS -> injectStates()
                State.FAILED ->{
                   if (it.code == ERROR_CODE_INIT){
                       injectStates(error = !hasCache)
                       mBinding?.indicator = if(hasCache)"news" else "--"
                   }else{
                       requireContext().toast("no network")
                   }
                }
            }
        })

        mViewModel.articles?.observe(this, Observer {
            mAdapter.submitList(it)
        })


    }

    private fun injectStates(refreshing: Boolean = false, loading: Boolean = false, error: Boolean = false){
        mBinding?.let {binding->
            binding.refreshing = refreshing
            binding.loadingStatus = loading
            binding.errorStatus = error
        }
    }
}

