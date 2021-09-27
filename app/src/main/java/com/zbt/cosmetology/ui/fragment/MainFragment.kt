package com.zbt.cosmetology.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.init
import com.zbt.cosmetology.app.ext.initMain
import com.zbt.cosmetology.app.ext.interceptLongClick
import com.zbt.cosmetology.app.ext.setUiTheme
import com.zbt.cosmetology.databinding.FragmentMainBinding
import com.zbt.cosmetology.viewmodel.state.MainViewModel

/**
 * Time　: 2019/12/27
 * Author　: zbt
 * Description　:项目主页Fragment
 */
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override fun layoutId() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        //初始化 bottomBar
        mainBottom.init{
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_project -> mainViewpager.setCurrentItem(1, false)
                R.id.menu_system -> mainViewpager.setCurrentItem(2, false)
                R.id.menu_public -> mainViewpager.setCurrentItem(3, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(4, false)
            }
        }
        mainBottom.interceptLongClick(R.id.menu_main,R.id.menu_project,R.id.menu_system,R.id.menu_public,R.id.menu_me)
    }

    override fun createObserver() {
        appViewModel.appColor.observe(viewLifecycleOwner, Observer {
            setUiTheme(it, mainBottom)
        })
    }
}