package com.dohia.androidbaseframework.activity

import android.os.Bundle
import android.os.Handler
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.layout.WelcomeUI
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

/**
Date: 2018/10/29
Time: 09:15
author: duhaitao
 */
class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WelcomeUI().setContentView(this)
        Handler().postDelayed({
            startActivity<MainActivity>()
            finish()
        },3000)
    }

}