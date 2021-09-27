package com.zbt.cosmetology.app.weight.loadCallBack


import com.kingja.loadsir.callback.Callback
import com.zbt.cosmetology.R


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}