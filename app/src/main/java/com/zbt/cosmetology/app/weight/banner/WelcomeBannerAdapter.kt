package com.zbt.cosmetology.app.weight.banner

/**
 * Author　: zbt
 * Time　: 2020/2/20
 * Description　:
 */

import android.view.View
import com.zhpan.bannerview.BaseBannerAdapter
import com.zbt.cosmetology.R

class WelcomeBannerAdapter : BaseBannerAdapter<Int, WelcomeBannerViewHolder>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_itemwelcome
    }

    override fun createViewHolder(itemView: View, viewType: Int): WelcomeBannerViewHolder {
        return WelcomeBannerViewHolder(itemView);
    }

    override fun onBind(
        holder: WelcomeBannerViewHolder?,
        data: Int?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data, position, pageSize);
    }
}
