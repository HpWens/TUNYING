package com.tunyin.utils

import com.tunyin.mvp.model.Event
import org.greenrobot.eventbus.EventBus


/**
 * create by wangrongchao
 * on 2019/12/11
 */
object EventBusUtil {

    fun register(subscriber: Any) {
        EventBus.getDefault().register(subscriber)
    }

    fun unregister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }

    fun sendEvent(event: Event<*>) {
        EventBus.getDefault().post(event)
    }

    fun sendStickyEvent(event: Event<*>) {
        EventBus.getDefault().postSticky(event)
    }

    fun formatSecond(time: Int): String {
        var min = (time / (1000 * 60)).toString() + ""
        var second = (time % (1000 * 60) / 1000).toString() + ""
        if (min.length < 2) {
            min = "0$min"
        }
        if (second.length < 2) {
            second = "0$second"
        }
        return second
    }

}
