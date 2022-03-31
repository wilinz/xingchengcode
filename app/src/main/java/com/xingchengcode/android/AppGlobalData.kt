package com.xingchengcode.android

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KParameter

/**
 * 全局的API接口。
 *
 */
object AppGlobalData {
    private var dataStore = DataStoreUtils
    private const val KEY_PHONE = "phone"
    private const val KEY_PLACE = "place"
    /**
     * 初始化接口。这里会进行应用程序的初始化操作，一定要在代码执行的最开始调用。
     *
     */
    fun initialize(dataStoreUtils: DataStoreUtils) {
        dataStore = dataStoreUtils
    }

    fun getPhone(): Flow<String> {
        return dataStore.getData(KEY_PHONE,"")
    }

    fun setPhone(phone:String) {
        dataStore.saveSyncStringData(KEY_PHONE,phone)
    }

    fun getPlace(): Flow<String> {
        return dataStore.getData(KEY_PLACE,"")
    }

    fun setPlace(place:String) {
        dataStore.saveSyncStringData(KEY_PLACE,place)
    }
}