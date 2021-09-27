package com.zbt.cosmetology.viewmodel.state

import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.common.callback.databind.StringObservableField

/**
 * Author　: zbt
 * Time　: 2020/3/11
 * Description　:
 */
class AriticleViewModel : BaseViewModel() {

    //分享文章标题
    var shareTitle = StringObservableField()

    //分享文章网址
    var shareUrl = StringObservableField()

    //分享文章的人
    var shareName = StringObservableField()

}