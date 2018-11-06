package com.dohia.androidbaseframework.utils

import android.os.Handler
import android.util.Log
import java.util.*

/**
Date: 2018/10/31
Time: 14:59
author: duhaitao
 */
class TimerUtils {

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    private fun timeTask(handler: Handler) {
        stopTime()
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                //执行的操作
                var message = handler.obtainMessage()
                message.what = 1
                message.sendToTarget()
            }
        }
    }

    fun startTime(handler: Handler) {
        timeTask(handler)
        timer?.schedule(timerTask,10,10000)//1000 = 1s
    }

     fun stopTime() {
        if (timerTask != null) {
            timer?.cancel()
            timer = null
            timerTask!!.cancel()
            timerTask = null
        }
    }
}