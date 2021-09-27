package com.zbt.cosmetology.ui.activity

import android.content.Intent
import android.os.Bundle
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseActivity
import com.zbt.cosmetology.databinding.ActivityTestBinding
import kotlinx.android.synthetic.main.activity_test.*

/**
 *Author: zbt
 *Time: 2021/5/8 15:55
 *Description: This is TestSecondActivity
 */
class TestSecondActivity : BaseActivity<BaseViewModel, ActivityTestBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        button.setOnClickListener {
//            viewModel.loginReq("123", "123456")
            startActivityForResult(Intent(this, TestActivity::class.java), 102)
        }
    }
}