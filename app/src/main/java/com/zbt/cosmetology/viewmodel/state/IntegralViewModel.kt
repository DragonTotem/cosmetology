package com.zbt.cosmetology.viewmodel.state

import androidx.databinding.ObservableField
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.data.model.bean.IntegralResponse

/**
 * Author　: zbt
 * Time　: 2020/3/10
 * Description　:
 */
class IntegralViewModel : BaseViewModel() {

    var rank = ObservableField<IntegralResponse>()
}