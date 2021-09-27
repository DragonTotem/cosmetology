package com.zbt.cosmetology.viewmodel.state

import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.common.callback.databind.IntObservableField
import com.zbt.common.callback.databind.StringObservableField
import com.zbt.common.callback.livedata.UnPeekLiveData
import com.zbt.cosmetology.app.util.ColorUtil

/**
 * Author　: zbt
 * Time　: 2019/12/27
 * Description　: 专门存放 MeFragment 界面数据的ViewModel
 */
class MeViewModel : BaseViewModel() {

    var name = StringObservableField("请先登录~")

    var integral = IntObservableField(0)

    var info = StringObservableField("id：--　排名：-")

    var imageUrl = StringObservableField(ColorUtil.randomImage())

    var testString = UnPeekLiveData<String>()
}