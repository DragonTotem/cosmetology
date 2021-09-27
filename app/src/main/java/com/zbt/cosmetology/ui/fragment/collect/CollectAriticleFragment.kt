package com.zbt.cosmetology.ui.fragment.collect

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_recyclerview.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.*
import com.zbt.cosmetology.app.weight.recyclerview.SpaceItemDecoration
import com.zbt.cosmetology.data.model.bean.CollectBus
import com.zbt.cosmetology.databinding.IncludeListBinding
import com.zbt.cosmetology.ui.adapter.CollectAdapter
import com.zbt.cosmetology.viewmodel.request.RequestCollectViewModel
import com.zbt.common.ext.nav
import com.zbt.common.ext.navigateAction

/**
 * Author　: zbt
 * Time　: 2020/3/10
 * Description　: 收藏的文章集合Fragment
 */
class CollectAriticleFragment : BaseFragment<RequestCollectViewModel, IncludeListBinding>() {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    private val articleAdapter: CollectAdapter by lazy { CollectAdapter(arrayListOf()) }

    override fun layoutId() = R.layout.include_list

    override fun initView(savedInstanceState: Bundle?)  {
        //状态页配置
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
            mViewModel.getCollectAriticleData(true)
        }
        //初始化recyclerView
        recyclerView.init(LinearLayoutManager(context), articleAdapter).let {
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f)))
            it.initFooter(SwipeRecyclerView.LoadMoreListener {
                mViewModel.getCollectAriticleData(false)
            })
            //初始化FloatingActionButton
            it.initFloatBtn(floatbtn)
        }
        //初始化 SwipeRefreshLayout
        swipeRefresh.init {
            //触发刷新监听时请求数据
            mViewModel.getCollectAriticleData(true)
        }
        articleAdapter.run {
            setCollectClick { item, v, position ->
                if (v.isChecked) {
                    mViewModel.uncollect(item.originId)
                } else {
                    mViewModel.collect(item.originId)
                }
            }
            setOnItemClickListener { _, view, position ->
                nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                        putParcelable("collect", articleAdapter.data[position])
                    })
            }
        }
    }

    override fun lazyLoadData() {
        loadsir.showLoading()
        mViewModel.getCollectAriticleData(true)
    }

    override fun createObserver() {
        mViewModel.ariticleDataState.observe(viewLifecycleOwner, Observer {
            //设值 新写了个拓展函数，搞死了这个恶心的重复代码
            loadListData(it, articleAdapter, loadsir, recyclerView,swipeRefresh)
        })
        mViewModel.collectUiState.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                //收藏或取消收藏操作成功，发送全局收藏消息
                eventViewModel.collectEvent.value = CollectBus(it.id, it.collect)
            } else {
                showMessage(it.errorMsg)
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].originId == it.id) {
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
                    if (articleAdapter.data[index].originId == it.id) {
                        articleAdapter.remove(index)
                        if (articleAdapter.data.size == 0) {
                            loadsir.showEmpty()
                        }
                        return@Observer
                    }
                }
                mViewModel.getCollectAriticleData(true)
            })
        }
    }

}