package com.zbt.cosmetology.ui.fragment.collect

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.kingja.loadsir.core.LoadService
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_recyclerview.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.*
import com.zbt.cosmetology.app.weight.recyclerview.SpaceItemDecoration
import com.zbt.cosmetology.databinding.IncludeListBinding
import com.zbt.cosmetology.ui.adapter.CollectUrlAdapter
import com.zbt.cosmetology.viewmodel.request.RequestCollectViewModel
import com.zbt.common.ext.nav
import com.zbt.common.ext.navigateAction

/**
 * Author　: zbt
 * Time　: 2020/3/10
 * Description　: 收藏的网址集合Fragment
 */
class CollectUrlFragment : BaseFragment<RequestCollectViewModel, IncludeListBinding>() {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    private val articleAdapter: CollectUrlAdapter by lazy { CollectUrlAdapter(arrayListOf()) }

    override fun layoutId() = R.layout.include_list

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            mViewModel.getCollectUrlData()

        }
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context), articleAdapter).let {
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f)))
            //初始化FloatingActionButton
            it.initFloatBtn(floatbtn)
        }
        //初始化 SwipeRefreshLayout
        swipeRefresh.init {
            //触发刷新监听时请求数据
            mViewModel.getCollectUrlData()
        }
        articleAdapter.run {
            setCollectClick { item, v, position ->
                if (v.isChecked) {
                    mViewModel.uncollectUrl(item.id)
                } else {
                    mViewModel.collectUrl(item.name, item.link)
                }
            }
            setOnItemClickListener { _, view, position ->
                nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                    putParcelable("collectUrl", articleAdapter.data[position])
                })
            }
        }
    }

    override fun lazyLoadData() {
        //设置界面 加载中
        loadsir.showLoading()
        mViewModel.getCollectUrlData()
    }

    override fun createObserver() {
        mViewModel.urlDataState.observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = false
            recyclerView.loadMoreFinish(it.isEmpty, it.hasMore)
            if (it.isSuccess) {
                //成功
                when {
                    //第一页并没有数据 显示空布局界面
                    it.isEmpty -> {
                        loadsir.showEmpty()
                    }
                    else -> {
                        loadsir.showSuccess()
                        articleAdapter.setList(it.listData)
                    }
                }
            } else {
                //失败
                loadsir.showError(it.errMessage)
            }
        })
        mViewModel.collectUrlUiState.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].id == it.id) {
                        articleAdapter.remove(index)
                        if (articleAdapter.data.size == 0) {
                            loadsir.showEmpty()
                        }
                        return@Observer
                    }
                }
            } else {
                showMessage(it.errorMsg)
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].id == it.id) {
                        articleAdapter.notifyItemChanged(index)
                        break
                    }
                }
            }
        })
        eventViewModel.run {
            //监听全局的收藏信息 收藏的Id跟本列表的数据id匹配则 需要删除他 否则则请求最新收藏数据
            collectEvent.observe(viewLifecycleOwner, Observer {
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].id == it.id) {
                        articleAdapter.data.removeAt(index)
                        articleAdapter.notifyItemChanged(index)
                        if (articleAdapter.data.size == 0) {
                            loadsir.showEmpty()
                        }
                        return@Observer
                    }
                }
                mViewModel.getCollectUrlData()
            })
        }
    }

}