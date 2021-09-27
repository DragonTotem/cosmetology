package com.zbt.common.network.manager

import com.zbt.common.callback.livedata.UnPeekLiveData

/**
 * Author　: zbt
 * Time　: 2020/5/2
 * Description　: 网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = UnPeekLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }

}