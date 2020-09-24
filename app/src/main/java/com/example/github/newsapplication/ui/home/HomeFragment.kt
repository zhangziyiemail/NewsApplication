package com.example.github.newsapplication.ui.home


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.github.newsapplication.MyApplication
import com.example.github.newsapplication.R
import com.example.github.newsapplication.Utils.PreferencesHelper
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.base.ERROR_CODE_INIT
import com.example.github.newsapplication.base.ErrorReload
import com.example.github.newsapplication.base.OnItemClickListener
import com.example.github.newsapplication.base.OnItemLongClickListener
import com.example.github.newsapplication.data.MyDatabaseUtils
import com.example.github.newsapplication.databinding.FragmentHomeBinding
import com.example.github.newsapplication.databinding.NavHeaderMainBinding
import com.example.github.newsapplication.entity.NewsData
import com.example.github.newsapplication.entity.ObservableUser
import com.example.github.newsapplication.entity.State
import com.example.github.newsapplication.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val mAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity(), HomeViewmModelFactory(NewsListRepository()))
            .get(HomeViewModel::class.java)
    }

    private val mHeaderBinding by lazy {
        DataBindingUtil.inflate<NavHeaderMainBinding>(
            layoutInflater, R.layout.nav_header_main, mBinding?.userProfileDrawer, false
        )
    }

    private var hasCache = true

    override fun actionsOnViewInflate() {
        mBinding?.let {
            it.userProfileDrawer.addHeaderView(mHeaderBinding.root)
        }
        mViewModel.fetchHomeNews {  }
        mViewModel.netState?.observe(this, Observer {
            when (it.status) {
                State.RUNNING -> injectStates(true, loading = !hasCache)
                State.SUCCESS -> injectStates()
                State.FAILED -> {
                    if (it.code == ERROR_CODE_INIT) {
                        injectStates(error = !hasCache)

                    } else {
                        requireContext().toast("no network")
                    }
                }
            }
        })

        mViewModel.articles?.observe(this, Observer {
            mAdapter.update(it)
        })



    }

    override fun getLayoutId() = R.layout.fragment_home

    @SuppressLint("ClickableViewAccessibility")
    override fun initFragment(view: View, savedInstanceState: Bundle?) {

        mHeaderBinding?.let {
            binding->
                binding.user = ObservableUser
                binding.viewclick=View.OnClickListener {
                    if (!PreferencesHelper.hasLogin(MyApplication.instance)) toRegistFragment() else toLoginFragment()
                }
        }
        mBinding?.let { binding ->
            binding.refreshColor = R.color.colorPrimary
            binding.refreshListener = SwipeRefreshLayout.OnRefreshListener {
                mViewModel.articles?.observe(this, Observer {
                    mAdapter.update(it)
                })
            }

            binding.adapter = mAdapter

            binding.itemLongClick =
                OnItemLongClickListener { position, _ ->
                    mAdapter.getItemData(position)?.let { article ->
                        showCollectDialog(article)
                    }
                }
            binding.itemClick =
                OnItemClickListener { position, v ->
                    mAdapter.getItemData(position)?.let { art ->
                        DetailFragment.viewDetail(
                            mNavController,
                            R.id.action_homeFragment_to_detailFragment,
                            art.title,
                            art.urlToImage,
                            art.content
                        )
                    }
                }

            binding.errorReload = ErrorReload {

            }



        }

        mBinding?.userProfileDrawer?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorites -> toFavorites()
                R.id.nav_sendBroadcast -> sendMyBroadcast()
            }
            true
        }
    }

     fun sendMyBroadcast() {
        Intent().also {
            it.setAction("com.example.broadcast.MY_NOTIFICATION")
            it.putExtra("type", "notfied")
            it.putExtra("title", "Notice me senpai!")
            it.putExtra("data", "Notice me data!")
            requireContext().sendBroadcast(it)
        }
         user_profile_drawer.menu.close()
    }


    private fun toRegistFragment() {
        mNavController.navigate(R.id.action_homeFragment_to_loginFragment)
        mBinding?.drawer?.closeDrawer(GravityCompat.START)
    }

    private fun toLoginFragment() {
        mNavController.navigate(R.id.action_homeFragment_to_registerFragment)
        mBinding?.drawer?.closeDrawer(GravityCompat.START)
    }

    private fun showCollectDialog(article: NewsData) {
        var collect : Boolean = false
        GlobalScope.launch(Dispatchers.IO) {
            collect = article.title?.let { MyDatabaseUtils.newsArticleCacheDao.findArticle(it) } == null
        }
        requireContext().alert(
            if (collect)"「${article.title}」collected"
        else "collect 「${article.title}」"
        ) {
            yesButton {
                GlobalScope.launch (Dispatchers.IO){
                    MyDatabaseUtils.newsArticleCacheDao.cacheHomeArticles(article)
                }

            }
            noButton {

            }
        }.show()
    }


    private fun toFavorites(){
        mNavController.navigate(R.id.action_homeFragment_to_favoritesFragment)
        mBinding?.drawer?.closeDrawer(GravityCompat.START)
    }

    private fun injectStates(
        refreshing: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        mBinding?.let { binding ->
            binding.refreshing = refreshing
            binding.loadingStatus = loading
            binding.errorStatus = error
        }
    }



}

