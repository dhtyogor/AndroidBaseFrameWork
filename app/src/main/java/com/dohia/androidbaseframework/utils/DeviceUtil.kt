package com.dohia.androidbaseframework.utils

import android.content.Context
import android.telephony.TelephonyManager
import com.dohia.androidbaseframework.config.MyApp


/**
Date: 2018/11/29
Time: 16:56
author: duhaitao
 */
class DeviceUtil {

    /**
     * 获取手机品牌
     * @return
     */
    fun getDeviceBrand()= android.os.Build.BRAND!!

    /**
     * 获取手机型号
     * @return
     */
    fun getDeviceModel() = android.os.Build.MODEL!!

    /**
     * 获取当前手机系统版本号
     */
    fun getDeviceVersion() = android.os.Build.VERSION.RELEASE!!

    /**
     * 获取设备的唯一标识，deviceId
     * @return
     */
    fun getDeviceId(): String {
        var tm = MyApp.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var deviceId = tm.deviceId
        return deviceId ?: "-1"
    }


}