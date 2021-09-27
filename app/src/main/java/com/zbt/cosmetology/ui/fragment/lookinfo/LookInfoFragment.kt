package com.zbt.cosmetology.ui.fragment.lookinfo

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.kingja.loadsir.core.LoadService
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.fragment_lookinfo.*
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_recyclerview.*
import kotlinx.android.synthetic.main.include_toolbar.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.*
import com.zbt.cosmetology.app.weight.recyclerview.SpaceItemDecoration
import com.zbt.cosmetology.data.model.bean.CollectBus
import com.zbt.cosmetology.databinding.FragmentLookinfoBinding
import com.zbt.cosmetology.ui.adapter.AriticleAdapter
import com.zbt.cosmetology.viewmodel.request.RequestCollectViewModel
import com.zbt.cosmetology.viewmodel.request.RequestLookInfoViewModel
import com.zbt.cosmetology.viewmodel.state.LookInfoViewModel
import com.zbt.common.ext.nav
import com.zbt.common.ext.navigateAction

/**
 * Author　: zbt
 * Time　: 2020/3/4
 * Description　: 查看他人信息
 */
class LookInfoFragment : BaseFragment<LookInfoViewModel, FragmentLookinfoBinding>() {

    //对方的Id
    private var shareId = 0

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    //适配器
    private val articleAdapter: AriticleAdapter by lazy { AriticleAdapter(arrayListOf(), true) }

    //收藏viewmodel
    private val requestCollectViewModel: RequestCollectViewModel by viewModels()

    //专门负责请求数据的ViewModel
    private val requestLookInfoViewModel: RequestLookInfoViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_lookinfo

    override fun initView(savedInstanceState: Bundle?) {
        arguments?.let {
            shareId = it.getInt("id")
        }
        mDatabind.vm = mViewModel

        appViewModel.appColor.value?.let { share_layout.setBackgroundColor(it) }

        toolbar.initClose("他的信息") {
            nav().navigateUp()
        }
        loadsir = loadServiceInit(share_linear) {
            loadsir.showLoading()
            requestLookInfoViewModel.getLookinfo(shareId, true)
        }
        recyclerView.init(LinearLayoutManager(context), articleAdapter).let {
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f)))
            it.initFooter(SwipeRecyclerView.LoadMoreListener {
                requestLookInfoViewModel.getLookinfo(shareId, false)
            })
            it.initFloatBtn(floatbtn)
        }
        swipeRefresh.init {
            requestLookInfoViewModel.getLookinfo(shareId, true)
        }
        articleAdapter.run {
            setCollectClick { item, v, position ->
                if (v.isChecked) {
                    requestCollectViewModel.uncollect(item.id)
                } else {
                    requestCollectViewModel.collect(item.id)
                }
            }
            setOnItemClickListener { adapter, view, position ->
                nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                    putParcelable(
                        "ariticleData",
                        articleAdapter.data[position - this@LookInfoFragment.recyclerView.headerCount]
                    )
                })
            }
        }
    }

    override fun lazyLoadData() {
        //设置界面 加载中
        loadsir.showLoading()
        requestLookInfoViewModel.getLookinfo(shareId, true)
    }

    override fun createObserver() {
        requestLookInfoViewModel.shareResponse.observe(viewLifecycleOwner, Observer {
            mViewModel.name.set(it.coinInfo.username)
            mViewModel.info.set("积分 : ${it.coinInfo.coinCount}　排名 : ${it.coinInfo.rank}")
        })
        requestLookInfoViewModel.shareListDataUistate.observe(viewLifecycleOwner, Observer {
            //设值 新写了个拓展函数，搞死了这个恶心的重复代码
            loadListData(it, articleAdapter, loadsir, recyclerView, swipeRefresh)
        })
        requestCollectViewModel.collectUiState.observe(viewLifecycleOwner, Observer {
            if (it.isSuccess) {
                //收藏或取消收藏操作成功，发送全局收藏消息
                eventViewModel.collectEvent.value = CollectBus(it.id, it.collect)
            } else {
                showMessage(it.errorMsg)
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].id == it.id) {
                        articleAdapter.data[index].collect = it.collect
                        articleAdapter.notifyItemChanged(index)
                        break
                    }
                }
            }
        })
        appViewModel.run {
            //监听账户信息是否改变 有值时(登录)将相关的数据设置为已收藏，为空时(退出登录)，将已收藏的数据变为未收藏
            userinfo.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    it.collectIds.forEach { id ->
                        for (item in articleAdapter.data) {
                            if (id.toInt() == item.id) {
                                item.collect = true
                                break
                            }
                        }
                    }
                } else {
                    for (item in articleAdapter.data) {
                        item.collect = false
                    }
                }
                articleAdapter.notifyDataSetChanged()
            })
            //监听全局的收藏信息 收藏的Id跟本列表的数据id匹配则需要更新
            eventViewModel.collectEvent.observe(viewLifecycleOwner, Observer {
                for (index in articleAdapter.data.indices) {
                    if (articleAdapter.data[index].id == it.id) {
                        articleAdapter.data[index].collect = it.collect
                        articleAdapter.notifyItemChanged(index)
                        break
                    }
                }
            })
        }
    }
}