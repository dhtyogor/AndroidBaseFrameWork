package com.dohia.androidbaseframework.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.dohia.androidbaseframework.config.MyApp

/**
Date: 2018/11/8
Time: 09:48
author: duhaitao
 网络工具类
 */
class NetworkUtil {

    companion object {

        /**
         * 网络是否连接
         * */
        fun isConnected(): Boolean {
            val manager = MyApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            if (activeInfo != null) {
                return activeInfo.isConnected
            }
            return false
        }

        /**
         * 网络是否可用
         * */
        fun isAvailable(): Boolean {
            val manager = MyApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            if (activeInfo != null) {
                return activeInfo.isAvailable
            }
            return false
        }

        /**
         * 是否连接WIFI
         * */
        fun isWifiConnected(): Boolean {
            val manager = MyApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            if ((activeInfo != null) and (activeInfo.type == ConnectivityManager.TYPE_WIFI)) {
                return true
            }
            return false
        }

        /**
         * 是否连接mobile
         * */
        fun isMobileConnected(): Boolean {
            val manager = MyApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            if ((activeInfo != null) and (activeInfo.type == ConnectivityManager.TYPE_MOBILE)) {
                return true
            }
            return false
        }

        /**
         * 获取当前网络连接的类型信息
         * */
        fun getConnectedType(): Int {
            val manager = MyApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            if (activeInfo != null && activeInfo.isAvailable) {
                return activeInfo.type
            }
            return -1
        }

    }



}