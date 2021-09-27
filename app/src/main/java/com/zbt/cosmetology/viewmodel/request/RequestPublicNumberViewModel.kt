package com.zbt.cosmetology.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.app.network.apiService
import com.zbt.cosmetology.app.network.stateCallback.ListDataUiState
import com.zbt.cosmetology.data.model.bean.AriticleResponse
import com.zbt.cosmetology.data.model.bean.ClassifyResponse
import com.zbt.common.ext.request
import com.zbt.common.state.ResultState

/**
 * Author　: zbt
 * Time　: 2020/2/29
 * Description　:
 */
class RequestPublicNumberViewModel : BaseViewModel() {

    var pageNo = 1

    var titleData: MutableLiveData<ResultState<ArrayList<ClassifyResponse>>> = MutableLiveData()

    var publicDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()


    fun getPublicTitleData() {
        request({ apiService.getPublicTitle() }, titleData)
    }

    fun getPublicData(isRefresh: Boolean, cid: Int) {
        if (isRefresh) {
            pageNo = 1
        }
        request({ apiService.getPublicData(pageNo, cid) }, {
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            publicDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            publicDataState.value = listDataUiState
        })
    }
}