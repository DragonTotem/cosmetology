package com.zbt.cosmetology.viewmodel.state

import com.zbt.common.base.viewmodel.BaseViewModel
import com.zbt.common.callback.databind.IntObservableField
import com.zbt.common.callback.databind.StringObservableField
import com.zbt.cosmetology.data.model.enums.TodoType
import com.zbt.common.ext.launch

/**
 * Author　: zbt
 * Time　: 2020/3/11
 * Description　:
 */
class TodoViewModel : BaseViewModel() {

    //标题
    var todoTitle = StringObservableField()

    //内容
    var todoContent =
        StringObservableField()

    //Time
    var todoTime = StringObservableField()

    //优先级
    var todoLeve =
        StringObservableField(TodoType.TodoType1.content)

    //优先级颜色
    var todoColor =
        IntObservableField(TodoType.TodoType1.color)

    fun xx(): Unit {
        launch({

        },{

        })
    }
}