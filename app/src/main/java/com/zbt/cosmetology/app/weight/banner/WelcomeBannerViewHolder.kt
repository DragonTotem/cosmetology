package com.zbt.cosmetology.app.weight.banner

/**
 * Author　: zbt
 * Time　: 2020/2/20
 * Description　:
 */

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zhpan.bannerview.BaseViewHolder
import com.zbt.cosmetology.R

class WelcomeBannerViewHolder(view: View) : BaseViewHolder<Int>(view) {

    override fun bindData(data: Int?, position: Int, pageSize: Int) {
//        val textView = findView<TextView>(R.id.banner_text)
//        textView.text = data

        data?.let {
            val imageView = findView<ImageView>(R.id.iv_banner)
            imageView.setImageResource(it)
        }
    }
}
