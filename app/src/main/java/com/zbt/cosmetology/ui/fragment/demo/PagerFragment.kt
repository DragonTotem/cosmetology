package com.zbt.cosmetology.ui.fragment.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_pager.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.databinding.FragmentPagerBinding
import com.zbt.cosmetology.ui.fragment.collect.CollectFragment
import com.zbt.cosmetology.ui.fragment.search.SearchFragment
import com.zbt.cosmetology.ui.fragment.share.AriticleFragment
import com.zbt.cosmetology.ui.fragment.todo.TodoListFragment
import com.zbt.cosmetology.viewmodel.state.MainViewModel

/**
 * @author : hgj
 * @date   : 2020/6/15
 * 测试 Viewpager下的懒加载
 */
class PagerFragment : BaseFragment<MainViewModel, FragmentPagerBinding>() {

    override fun layoutId() = R.layout.fragment_pager

    override fun initView(savedInstanceState: Bundle?) {
        pagerViewpager.adapter = object : FragmentStatePagerAdapter(childFragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        SearchFragment()
                    }
                    1 -> {
                        TodoListFragment()
                    }
                    2 -> {
                        AriticleFragment()
                    }
                    else -> {
                        CollectFragment()
                    }
                }
            }

            override fun getCount(): Int {
                return 5;
            }
        }
        pagerViewpager.offscreenPageLimit = 5
    }
}