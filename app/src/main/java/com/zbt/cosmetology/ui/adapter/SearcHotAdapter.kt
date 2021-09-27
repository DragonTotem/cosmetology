package com.zbt.cosmetology.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zbt.cosmetology.R
import com.zbt.cosmetology.app.ext.setAdapterAnimation
import com.zbt.cosmetology.app.util.ColorUtil
import com.zbt.cosmetology.app.util.SettingUtil
import com.zbt.cosmetology.data.model.bean.SearchResponse

class SearcHotAdapter(data: ArrayList<SearchResponse>) :
    BaseQuickAdapter<SearchResponse, BaseViewHolder>(R.layout.flow_layout, data) {

    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun convert(holder: BaseViewHolder, item: SearchResponse) {
        holder.setText(R.id.flow_tag, item.name)
        holder.setTextColor(R.id.flow_tag, ColorUtil.randomColor())
    }

}