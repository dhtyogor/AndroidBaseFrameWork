package com.dohia.androidbaseframework.instance

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import me.jessyan.autosize.internal.CancelAdapt

/**
Date: 2019/1/30
Time: 15:09
author: duhaitao
 */
//CancelAdapt:取消适配
class AutoSizeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_auto_size)
    }
}