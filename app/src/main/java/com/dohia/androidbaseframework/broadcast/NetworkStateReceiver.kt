package com.dohia.androidbaseframework.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Handler
import android.util.Log
import com.dohia.androidbaseframework.utils.NetworkUtil

/**
Date: 2018/11/1
Time: 15:07
author: duhaitao
 */
class NetworkStateReceiver : BroadcastReceiver() {

    private lateinit var setBattery: setBatteryText
    override fun onReceive(context: Context?, intent: Intent?) {
        var isNet = NetworkUtil.isConnected()
        setBattery.setText(isNet)
    }


    interface setBatteryText {
        fun setText(isNet: Boolean)
    }

    fun getBatteryText (setBattery: setBatteryText) {
        this.setBattery = setBattery
    }


}