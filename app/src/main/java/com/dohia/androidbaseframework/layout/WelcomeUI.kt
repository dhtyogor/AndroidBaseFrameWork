package com.dohia.androidbaseframework.layout

import android.widget.ImageView
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.activity.WelcomeActivity
import org.jetbrains.anko.*

/**
Date: 2018/10/29
Time: 09:16
author: duhaitao
 */
class WelcomeUI : AnkoComponent<WelcomeActivity> {

    override fun createView(ui: AnkoContext<WelcomeActivity>) = with(ui){
        verticalLayout {
            imageView {
                imageResource = R.mipmap.ic_launcher
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams {
                width = matchParent
                height = matchParent
            }
        }
    }
}