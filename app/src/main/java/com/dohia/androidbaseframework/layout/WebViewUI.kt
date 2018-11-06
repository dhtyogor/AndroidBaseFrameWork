package com.dohia.androidbaseframework.layout

import com.dohia.androidbaseframework.activity.WebViewActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout

/**
Date: 2018/11/6
Time: 14:19
author: duhaitao
 */
class WebViewUI : AnkoComponent<WebViewActivity> {
    override fun createView(ui: AnkoContext<WebViewActivity>) = with(ui) {
       relativeLayout {  }
    }
}