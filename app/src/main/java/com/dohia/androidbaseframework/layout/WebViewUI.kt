package com.dohia.androidbaseframework.layout

import android.view.View
import android.view.ViewManager
import android.webkit.WebView
import com.dohia.androidbaseframework.activity.WebViewActivity
import com.dohia.androidbaseframework.weight.StateButton
import com.youngtr.numberprogressbar.NumberProgressBar
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.custom.ankoView

/**
Date: 2018/11/6
Time: 14:19
author: duhaitao
 */
class WebViewUI : AnkoComponent<WebViewActivity> {

    companion object {
        lateinit var webView: WebView
        lateinit var numberProgressBar: NumberProgressBar
    }
    inline fun ViewManager.numberProgressBar(init: NumberProgressBar.() -> Unit) : NumberProgressBar{
        return ankoView({NumberProgressBar(it)},theme = 0,init = init)
    }
    inline fun ViewManager.stateButton(init: StateButton.() -> Unit) : StateButton{
        return ankoView({StateButton(it)},theme = 0,init = init)
    }
    override fun createView(ui: AnkoContext<WebViewActivity>) = with(ui) {
       linearLayout {
           webView = webView {  }.lparams(width = matchParent,height = matchParent)
           numberProgressBar = numberProgressBar {
               visibility = View.VISIBLE

           }.lparams(width = matchParent,height = wrapContent)
           stateButton {

           }
       }
    }
}
