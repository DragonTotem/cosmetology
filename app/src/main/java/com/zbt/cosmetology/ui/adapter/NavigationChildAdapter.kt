package com.zbt.cosmetology.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.ext.setAdapterAnimation
import com.zbt.cosmetology.app.util.ColorUtil
import com.zbt.cosmetology.app.util.SettingUtil
import com.zbt.cosmetology.data.model.bean.AriticleResponse
import com.zbt.common.ext.util.toHtml

class NavigationChildAdapter(data: ArrayList<AriticleResponse>) :
    BaseQuickAdapter<AriticleResponse, BaseViewHolder>(R.layout.flow_layout, data) {

    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun convert(holder: BaseViewHolder, item: AriticleResponse) {
        holder.setText(R.id.flow_tag,item.title.toHtml())
        holder.setTextColor(R.id.flow_tag, ColorUtil.randomColor())
    }

}