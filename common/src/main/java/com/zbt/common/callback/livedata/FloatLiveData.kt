package com.zbt.common.callback.livedata

import androidx.lifecycle.MutableLiveData


/**
 * Author　: zbt
 * Time　: 2019/12/17
 * Description　:自定义的Float类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class FloatLiveData(value: Float = 0f) : MutableLiveData<Float>(value) {
    override fun getValue(): Float {
        return super.getValue()!!
    }
}