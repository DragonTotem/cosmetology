package com.zbt.cosmetology.app.event

import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.common.callback.livedata.event.EventLiveData
import com.zbt.cosmetology.data.model.bean.CollectBus

/**
 * Author　: zbt
 * Time　: 2019/5/2
 * Description　:APP全局的ViewModel，可以在这里发送全局通知替代EventBus，LiveDataBus等
 */
class EventViewModel : BaseViewModel() {

    //全局收藏，在任意一个地方收藏或取消收藏，监听该值的界面都会收到消息
    val collectEvent = EventLiveData<CollectBus>()

    //分享文章通知
    val shareArticleEvent = EventLiveData<Boolean>()

    //添加TODO通知
    val todoEvent = EventLiveData<Boolean>()

}