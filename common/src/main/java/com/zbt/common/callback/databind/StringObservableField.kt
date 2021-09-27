package com.zbt.common.callback.databind

import androidx.databinding.ObservableField

/**
 * Author　: zbt
 * Time　: 2019/12/17
 * Description　:自定义的String类型 ObservableField  提供了默认值，避免取值的时候还要判空
 */
class StringObservableField(value: String = "") : ObservableField<String>(value) {

    override fun get(): String {
        return super.get()!!
    }

}