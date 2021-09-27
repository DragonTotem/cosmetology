package com.zbt.cosmetology.app.weight.banner

/**
 * Author　: zbt
 * Time　: 2020/2/20
 * Description　:
 */

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.zhpan.bannerview.BaseViewHolder
import com.zbt.common.base.appContext
import com.zbt.cosmetology.R
import com.zbt.cosmetology.data.model.bean.BannerResponse

class HomeBannerViewHolder(view: View) : BaseViewHolder<BannerResponse>(view) {
    override fun bindData(data: BannerResponse?, position: Int, pageSize: Int) {
        val img = itemView.findViewById<ImageView>(R.id.bannerhome_img)
        data?.let {
            Glide.with(appContext)
                .load(it.imagePath)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(img)
        }
    }

}
