package com.zbt.cosmetology.ui.fragment.demo

import android.os.Bundle
import kotlinx.android.synthetic.main.include_toolbar.*
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseFragment
import com.zbt.cosmetology.app.ext.initClose
import com.zbt.cosmetology.databinding.FragmentDemoBinding
import com.zbt.cosmetology.viewmodel.state.DemoViewModel
import com.zbt.common.ext.nav
import com.zbt.common.ext.navigateAction

/**放一些示例，目前只有 文件下载示例 后面想到什么加什么，Author那个比很懒，佛性添加
 * @author : hgj
 * @date   : 2020/7/13
 */
class DemoFragment : BaseFragment<DemoViewModel, FragmentDemoBinding>() {

    override fun layoutId() = R.layout.fragment_demo

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()

        toolbar.initClose("示例") {
            nav().navigateUp()
        }
    }


    inner class ProxyClick {
        fun download() {
            //测试一下 普通的下载
            nav().navigateAction(R.id.action_demoFragment_to_downLoadFragment)
        }

        fun downloadLibrary() {
            //测试一下利用三方库下载
            nav().navigateAction(R.id.action_demoFragment_to_downLoadLibraryFragment)
        }
    }


}