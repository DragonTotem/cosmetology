package com.zbt.cosmetology.app.weight.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.zbt.cosmetology.R


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}