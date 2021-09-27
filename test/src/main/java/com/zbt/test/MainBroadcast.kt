package com.zbt.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 *Author: zbt
 *Time: 2021/5/9 16:18
 *Description: This is MainBroadcast
 */
class MainBroadcast : BroadcastReceiver{

    constructor():super() {
        println("MainBroadCast ")

    }


    override fun onReceive(context: Context?, intent: Intent?) {
    }
}