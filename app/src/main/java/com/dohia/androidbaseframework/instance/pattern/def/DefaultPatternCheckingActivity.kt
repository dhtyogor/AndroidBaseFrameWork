package com.dohia.androidbaseframework.instance.pattern.def

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.utils.PatternHelper
import com.github.ihsg.patternlocker.OnPatternChangeListener
import com.github.ihsg.patternlocker.PatternLockerView
import kotlinx.android.synthetic.main.instance_activity_pattern_default_setting.*

/**
Date: 2018/11/23
Time: 14:29
author: duhaitao
 */
class DefaultPatternCheckingActivity : BaseActivity() {

    private lateinit var patternHelper: PatternHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_pattern_default_setting)
        initView()
    }

    private fun initView() {
        tvMsg.text = "设置解锁图案"
        patternHelper = PatternHelper()

        patternLockerView.setOnPatternChangedListener(object : OnPatternChangeListener {
            override fun onStart(view: PatternLockerView) {}
            override fun onChange(view: PatternLockerView, hitIndexList: List<Int>) {}
            override fun onComplete(view: PatternLockerView, hitIndexList: List<Int>) {
                var isOk = isPatternOk(hitIndexList)
                view.updateStatus(!isOk)
                patternIndicatorView!!.updateState(hitIndexList, !isOk)
                updateMsg()
            }
            override fun onClear(view: PatternLockerView) {finishIfNeeded()}
        })
    }

    private fun isPatternOk(hitIndexList: List<Int>): Boolean {
        this.patternHelper!!.validateForChecking(hitIndexList)
        return this.patternHelper.getIsOk()
    }

    private fun updateMsg() {
        tvMsg.text = this.patternHelper.getMessages()
        tvMsg.setTextColor(if (this.patternHelper.getIsOk())
            resources.getColor(R.color.colorPrimary)
        else
            resources.getColor(R.color.colorAccent))
    }

    private fun finishIfNeeded() {
        if (this.patternHelper.getIsFinish()) {
            finish()
        }
    }

}