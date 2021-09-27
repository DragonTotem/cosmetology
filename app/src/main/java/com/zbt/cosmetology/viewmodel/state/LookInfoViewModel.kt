package com.zbt.cosmetology.viewmodel.state

import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.common.callback.databind.StringObservableField

/**
 * Author　: zbt
 * Time　: 2020/3/4
 * Description　:
 */
class LookInfoViewModel : BaseViewModel() {

    var name = StringObservableField("--")

    var imageUrl =
        StringObservableField("https://upload.jianshu.io/users/upload_avatars/9305757/93322613-ff1a-445c-80f9-57f088f7c1b1.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/300/format/webp")

    var info = StringObservableField()


}