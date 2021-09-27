package com.zbt.test

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 *Author: zbt
 *Time: 2021/5/9 16:16
 *Description: This is Main
 */
class MainService : Service() {

    override fun onCreate() {
        super.onCreate()
        println("MainService onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}