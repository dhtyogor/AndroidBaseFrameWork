package com.dohia.androidbaseframework.instance

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.utils.TimerUtils
import kotlinx.android.synthetic.main.instance_activity_timer.*

/**
Date: 2018/11/1
Time: 09:49
author: duhaitao
 */
class TimerActivity : BaseActivity() {

    private var mTimerUtils = TimerUtils()

    private var runningDownTimer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_timer)
        initTimer()
        initDown()
    }

    private fun initTimer() {
        btnStart.setOnClickListener { mTimerUtils.startTime(handler) }
        btnStop.setOnClickListener { mTimerUtils.stopTime() }
    }

    private var handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what) {
                1 -> Log.e("xxx","定时中..........")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private fun initDown() {
        btnDown.setOnClickListener {
            if (runningDownTimer) {
                return@setOnClickListener
            }
            downTimer.start()
        }
    }

    private val downTimer = object : CountDownTimer((60 * 1000).toLong(),1000) {
        override fun onTick(millisUntilFinished: Long) {
            runningDownTimer = true
            btnDown.text = (millisUntilFinished / 1000).toString() + "s"
        }

        override fun onFinish() {
            runningDownTimer = false
            btnDown.text = "获取验证码"
        }
    }

}