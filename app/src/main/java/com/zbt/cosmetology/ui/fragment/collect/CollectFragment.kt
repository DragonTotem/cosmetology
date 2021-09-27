package com.zbt.cosmetology.ui.fragment.collect

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_collect.*
import kotlinx.android.synthetic.main.include_toolbar.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.viewmodel.request.RequestCollectViewModel
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.bindViewPager2
import com.zbt.cosmetology.app.ext.init
import com.zbt.cosmetology.app.ext.initClose
import com.zbt.cosmetology.databinding.FragmentCollectBinding
import com.zbt.common.ext.nav

/**
 * Author　: zbt
 * Time　: 2020/3/10
 * Description　: 收藏
 */
class CollectFragment:BaseFragment<RequestCollectViewModel,FragmentCollectBinding>() {

    var titleData = arrayListOf("文章","网址")

    private var fragments : ArrayList<Fragment> = arrayListOf()

    init {
        fragments.add(CollectAriticleFragment())
        fragments.add(CollectUrlFragment())
    }
    override fun layoutId() = R.layout.fragment_collect

    override fun initView(savedInstanceState: Bundle?)  {
        //初始化时设置顶部主题颜色
        appViewModel.appColor.value?.let { collect_viewpager_linear.setBackgroundColor(it) }
        //初始化viewpager2
        collect_view_pager.init(this,fragments)
        //初始化 magic_indicator
        collect_magic_indicator.bindViewPager2(collect_view_pager,mStringList = titleData)
        toolbar.initClose(){
            nav().navigateUp()
        }

    }
}