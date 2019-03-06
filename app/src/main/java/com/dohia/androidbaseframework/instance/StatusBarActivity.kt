package com.dohia.androidbaseframework.instance

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.gyf.barlibrary.ImmersionBar

/**
Date: 2019/1/8
Time: 16:49
author: duhaitao
 */
class StatusBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * .transparentBar() 沉浸式状态栏
         * .statusBarColor(R.color.colorAccent) 状态栏颜色
         * .statusBarDarkFont(false) true黑色 false白色
         * */
        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).statusBarDarkFont(false).init()
    }
}