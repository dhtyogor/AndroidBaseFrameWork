package com.dohia.androidbaseframework.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

/**
Date: 2018/11/1
Time: 15:07
author: duhaitao
 */
class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val connectivityManager = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo !=null && networkInfo.isAvailable) {
            Log.e("xxx","网络连接正常")
            getNet(true)
        } else {
            Log.e("xxx","网络连接异常")
            getNet(false)
        }
    }

    fun getNet(isNet: Boolean) : Boolean{

        return isNet
    }

}