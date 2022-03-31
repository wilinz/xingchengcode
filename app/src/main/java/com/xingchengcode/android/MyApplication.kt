package com.xiaoyuanpai

import android.app.Application
import android.content.Context
import com.xingchengcode.android.AppGlobalData
import com.xingchengcode.android.DataStoreUtils

class MyApplication : Application() {

    private var dataStore = DataStoreUtils

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        DataStoreUtils.init(applicationContext)
        AppGlobalData.initialize(dataStore)
    }
}