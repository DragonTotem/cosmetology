package com.zbt.cosmetology.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.cosmetology.app.network.apiService
import com.zbt.cosmetology.app.util.CacheUtil
import com.zbt.cosmetology.data.model.bean.ApiPagerResponse
import com.zbt.cosmetology.data.model.bean.AriticleResponse
import com.zbt.cosmetology.data.model.bean.SearchResponse
import com.zbt.common.ext.launch
import com.zbt.common.ext.request
import com.zbt.common.state.ResultState

/**
 * Author　: zbt
 * Time　: 2020/2/29
 * Description　:
 */
class RequestSearchViewModel : BaseViewModel() {

    var pageNo = 0

    //搜索热词数据
    var hotData: MutableLiveData<ResultState<ArrayList<SearchResponse>>> = MutableLiveData()

    //搜索结果数据
    var seachResultData: MutableLiveData<ResultState<ApiPagerResponse<ArrayList<AriticleResponse>>>> =
        MutableLiveData()

    //搜索历史词数据
    var historyData: MutableLiveData<ArrayList<String>> = MutableLiveData()

    /**
     * 获取热门数据
     */
    fun getHotData() {
        request({ apiService.getSearchData() }, hotData)
    }

    /**
     * 获取历史数据
     */
    fun getHistoryData() {
        launch({
            CacheUtil.getSearchHistoryData()
        }, {
            historyData.value = it
        }, {
            //获取本地历史数据出异常了
        })
    }

    /**
     * 根据字符串搜索结果
     */
    fun getSearchResultData(searchKey: String, isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request(
            { apiService.getSearchDataByKey(pageNo, searchKey) },
            seachResultData
        )
    }
}