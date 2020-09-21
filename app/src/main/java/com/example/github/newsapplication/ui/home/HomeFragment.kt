package com.example.github.newsapplication.ui.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.github.newsapplication.R
import com.example.github.newsapplication.base.BaseFragment
import com.example.github.newsapplication.base.ERROR_CODE_INIT
import com.example.github.newsapplication.base.ErrorReload
import com.example.github.newsapplication.base.OnItemClickListener
import com.example.github.newsapplication.base.OnItemLongClickListener
import com.example.github.newsapplication.databinding.FragmentHomeBinding
import com.example.github.newsapplication.entity.State
import com.example.github.newsapplication.ui.detail.DetailFragment
import org.jetbrains.anko.toast


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val mAdapter: HomeArticleAdapter by lazy { HomeArticleAdapter() }

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity(), HomeViewmModelFactory(NewsListRepository()))
            .get(HomeViewModel::class.java)
    }

    private val mCollectionViewModel by lazy{

    }

    private var hasCache = true

    override fun actionsOnViewInflate() {

        fetchHomeArticleList()
    }
    override fun getLayoutId()= R.layout.fragment_home

    @SuppressLint("ClickableViewAccessibility")
    override fun initFragment(view: View, savedInstanceState: Bundle?) {

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

                }
            binding.itemClick =
                OnItemClickListener { position, v ->
                    mAdapter.getItemData(position)?.let { art ->
                        Log.v("url",art.url)
                        Log.v("url",art.content)
                        DetailFragment.viewDetail(
                            mNavController,
                            R.id.action_homeFragment_to_detailFragment,
                            art.title,
                            art.urlToImage,
                            art.content
                        )
                    }
                }

//            binding.articleList.setOnTouchListener { _, _ ->
//                (parentFragment as? HomeFragment)?.closeMenu(true)
//                false
//            }

            binding.errorReload = ErrorReload {

            }
            binding.indicator = resources.getString(R.string.action_settings)
        }
    }

    fun  fetchHomeArticleList(){

        mViewModel.fetchHomeNews {

        }

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
//    private fun showCollectDialog(article: HomeFragment, position: Int) =
//        requireContext().alert(
//            if (article.collect) "「${article.title}」已收藏"
//            else " 是否收藏 「${article.title}」"
//        ) {
//            yesButton {
//                if (!article.collect) mCollectionViewModel.collectArticle(article.id, {
//                    mViewModel.articles?.value?.get(position)?.collect = true
//                    requireContext().toast("收藏成功")
//                }, { message ->
//                    requireContext().toast(message)
//                })
//            }
//            if (!article.collect) noButton { }
//        }.show()

    private fun injectStates(
        refreshing: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ){
        mBinding?.let { binding->
            binding.refreshing = refreshing
            binding.loadingStatus = loading
            binding.errorStatus = error
        }
    }


}

