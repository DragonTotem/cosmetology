package com.zbt.cosmetology.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.bannerview.BannerViewPager
import kotlinx.android.synthetic.main.activity_welcome.*
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.base.BaseActivity
import com.zbt.cosmetology.app.util.CacheUtil
import com.zbt.cosmetology.app.util.SettingUtil
import com.zbt.cosmetology.app.weight.banner.WelcomeBannerAdapter
import com.zbt.cosmetology.app.weight.banner.WelcomeBannerViewHolder
import com.zbt.cosmetology.databinding.ActivityWelcomeBinding
import com.zbt.common.ext.view.gone
import com.zbt.common.ext.view.visible

/**
 * Author　: zbt
 * Time　: 2020/2/22
 * Description　:
 */
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class WelcomeActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>() {

    private var resList = arrayOf(R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4, R.drawable.guide_5)

    private lateinit var mViewPager: BannerViewPager<Int , WelcomeBannerViewHolder>

    override fun layoutId() = R.layout.activity_welcome

    override fun initView(savedInstanceState: Bundle?) {
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        mDatabind.click = ProxyClick()
        welcome_baseview.setBackgroundColor(SettingUtil.getColor(this))
        mViewPager = findViewById(R.id.banner_view)
        if (CacheUtil.isFirst()) {
            //是第一次打开App 显示引导页
            welcome_image.gone()
            mViewPager.apply {
                adapter = WelcomeBannerAdapter()
                setLifecycleRegistry(lifecycle)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (position == resList.size - 1) {
                            welcomeJoin.visible()
                        } else {
                            welcomeJoin.gone()
                        }
                    }
                })
                create(resList.toList())
            }
        } else {
            //不是第一次打开App 3秒后自动跳转到主页
            welcome_image.visible()
            mViewPager.postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                //带点渐变动画
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }, 2000)
        }
    }

    inner class  ProxyClick {
        fun toMain() {
            CacheUtil.setFirst(false)
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            finish()
            //带点渐变动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

}