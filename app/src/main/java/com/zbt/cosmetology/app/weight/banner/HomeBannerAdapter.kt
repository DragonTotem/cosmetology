package com.zbt.cosmetology.app.weight.banner

/**
 * Author　: zbt
 * Time　: 2020/2/20
 * Description　:
 */

import android.view.View
import com.zhpan.bannerview.BaseBannerAdapter
import com.zbt.cosmetology.R
import com.zbt.cosmetology.data.model.bean.BannerResponse

class HomeBannerAdapter : BaseBannerAdapter<BannerResponse, HomeBannerViewHolder>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_itemhome
    }

    override fun createViewHolder(itemView: View, viewType: Int): HomeBannerViewHolder {
        return HomeBannerViewHolder(itemView);
    }

    override fun onBind(
        holder: HomeBannerViewHolder?,
        data: BannerResponse?,
        position: Int,
        pageSize: Int
    ) {
        holder?.bindData(data, position, pageSize);
    }


}
