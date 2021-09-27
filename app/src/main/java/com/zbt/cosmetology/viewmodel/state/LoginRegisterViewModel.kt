package com.zbt.cosmetology.viewmodel.state

import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.common.callback.databind.BooleanObservableField
import com.zbt.common.callback.databind.StringObservableField
import com.zbt.common.callback.livedata.StringLiveData

/**
 * Author　: zbt
 * Time　: 2019/12/23
 * Description　:登录注册的ViewModel
 */
class LoginRegisterViewModel : BaseViewModel() {

    //用户名
    var username = StringLiveData()

    //密码(登录注册界面)
    var password = StringObservableField()

    var password2 = StringObservableField()

    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanObservableField()

    var isShowPwd2 = BooleanObservableField()

}