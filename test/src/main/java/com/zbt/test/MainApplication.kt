package com.zbt.test

import android.app.Application
import android.content.Context

/**
 *Author: zbt
 *Time: 2021/5/9 16:14
 *Description: This is MainApplication
 */
class MainApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        println("MainApplication attachBaseContext()")
    }

    override fun onCreate() {
        super.onCreate()
        println("MainApplication onCreate()")
    }
}