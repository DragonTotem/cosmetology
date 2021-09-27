package com.zbt.cosmetology.app.weight.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.VibrateUtils
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.util.CacheUtil
import com.zbt.common.ext.nav
import com.zbt.common.ext.navigateAction


import per.goweii.reveallayout.RevealLayout

/**
 * @author CuiZhen
 */
class CollectView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RevealLayout(context, attrs, defStyleAttr), View.OnTouchListener {

    private var onCollectViewClickListener: OnCollectViewClickListener? = null

    override fun initAttr(attrs: AttributeSet) {
        super.initAttr(attrs)
        setCheckWithExpand(true)
        setUncheckWithExpand(false)
        setCheckedLayoutId(R.layout.layout_collect_view_checked)
        setUncheckedLayoutId(R.layout.layout_collect_view_unchecked)
        setAnimDuration(300)
        setAllowRevert(true)
        setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP ->{
                //震动一下
                VibrateUtils.vibrate(40)
                if(CacheUtil.isLogin()){
                    onCollectViewClickListener?.onClick(this)
                }else{
                    isChecked = true
                    nav(v).navigateAction(R.id.action_to_loginFragment)
                }

            }
        }
        return false
    }

    fun setOnCollectViewClickListener(onCollectViewClickListener: OnCollectViewClickListener) {
        this.onCollectViewClickListener = onCollectViewClickListener
    }

    interface OnCollectViewClickListener {
        fun onClick(v: CollectView)
    }
}