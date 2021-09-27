package com.zbt.cosmetology.ui.activity

import android.content.ClipData
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_error.*
import kotlinx.android.synthetic.main.include_toolbar.*
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseActivity
import com.zbt.cosmetology.app.ext.init
import com.zbt.cosmetology.app.ext.showMessage
import com.zbt.cosmetology.app.util.SettingUtil
import com.zbt.cosmetology.app.util.StatusBarUtil
import com.zbt.cosmetology.databinding.ActivityErrorBinding
import com.zbt.common.ext.util.clipboardManager
import com.zbt.common.ext.view.clickNoRepeat


/**
 * Author　: zbt
 * Time　: 2020/3/12
 * Description　:
 */
class ErrorActivity : BaseActivity<BaseViewModel, ActivityErrorBinding>() {

    override fun layoutId() = R.layout.activity_error

    override fun initView(savedInstanceState: Bundle?)  {


        toolbar.init("发生错误")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(SettingUtil.getColor(this)))
        StatusBarUtil.setColor(this, SettingUtil.getColor(this), 0)
        val config = CustomActivityOnCrash.getConfigFromIntent(intent)
        errorRestart.clickNoRepeat{
            config?.run {
//                CustomActivityOnCrash.restartApplication(this@ErrorActivity, this)
                ARouter.getInstance().build("ss").navigation();
            }
        }
//        errorSendError.clickNoRepeat {
//            CustomActivityOnCrash.getStackTraceFromIntent(intent)?.let {
//                showMessage(it,"发现有Bug不去打Author脸？","必须打",{
//                    val mClipData = ClipData.newPlainText("errorLog",it)
//                    // 将ClipData内容放到系统剪贴板里。
//                    clipboardManager?.setPrimaryClip(mClipData)
//                    ToastUtils.showShort("已复制错误日志")
//                    try {
//                        val url = "mqqwpa://im/chat?chat_type=wpa&uin=824868922"
//                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
//                    } catch (e: Exception) {
//                        ToastUtils.showShort("请先安装QQ")
//                    }
//                },"我不敢")
//            }
//
//
//        }
    }
}