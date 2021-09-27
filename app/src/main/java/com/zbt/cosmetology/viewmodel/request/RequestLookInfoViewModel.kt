package com.zbt.cosmetology.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.app.network.apiService
import com.zbt.cosmetology.app.network.stateCallback.ListDataUiState
import com.zbt.cosmetology.data.model.bean.AriticleResponse
import com.zbt.cosmetology.data.model.bean.ShareResponse
import com.zbt.common.ext.request

/**
 * Author　: zbt
 * Time　: 2020/3/4
 * Description　:
 */
class RequestLookInfoViewModel : BaseViewModel() {

    var pageNo = 1

    var shareListDataUistate = MutableLiveData<ListDataUiState<AriticleResponse>>()

    var shareResponse = MutableLiveData<ShareResponse>()

    fun getLookinfo(id: Int, isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 1
        }
        request({ apiService.getShareByidData(id, pageNo) }, {
            //请求成功
            pageNo++
            shareResponse.value = it
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = it.shareArticles.isRefresh(),
                    isEmpty = it.shareArticles.isEmpty(),
                    hasMore = it.shareArticles.hasMore(),
                    isFirstEmpty = isRefresh && it.shareArticles.isEmpty(),
                    listData = it.shareArticles.datas
                )
            shareListDataUistate.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            shareListDataUistate.value = listDataUiState
        })
    }
}