package com.zbt.cosmetology.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.app.network.apiService
import com.zbt.cosmetology.data.model.bean.IntegralResponse
import com.zbt.common.ext.request
import com.zbt.common.state.ResultState

/**
 * Author　: zbt
 * Time　: 2019/12/27
 * Description　:
 */
class RequestMeViewModel : BaseViewModel() {

    var meData = MutableLiveData<ResultState<IntegralResponse>>()

    fun getIntegral() {
        request({ apiService.getIntegral() }, meData)
    }
}