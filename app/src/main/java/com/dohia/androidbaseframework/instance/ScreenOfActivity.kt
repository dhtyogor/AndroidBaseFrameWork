package com.dohia.androidbaseframework.instance

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.utils.ISScreenXUtil
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.instance_activity_screen_of.*

/**
Date: 2019/1/4
Time: 17:24
author: duhaitao
 16：9  xxxhdpi(2560*1536)
 18:9/18.5:9  xxxhdpi(2960*1536)
 */
class ScreenOfActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_screen_of)
        ImmersionBar.with(this).transparentBar().init()
        var isScreen = ISScreenXUtil.isAllScreenDevice(this)
        if (isScreen) Glide.with(this@ScreenOfActivity).load(R.mipmap.start_page_x_icon).into(ivScreen)
        else Glide.with(this@ScreenOfActivity).load(R.mipmap.start_page_icon).into(ivScreen)
    }
}