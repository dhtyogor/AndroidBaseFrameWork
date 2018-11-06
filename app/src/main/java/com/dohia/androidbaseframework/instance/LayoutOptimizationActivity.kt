package com.dohia.androidbaseframework.instance

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity

/**
Date: 2018/11/1
Time: 09:54
author: duhaitao
 */
class LayoutOptimizationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_layout)
        initView()
    }

    private fun initView() {

    }

}