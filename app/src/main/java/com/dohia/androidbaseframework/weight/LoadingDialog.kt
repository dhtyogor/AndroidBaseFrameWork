package com.dohia.androidbaseframework.weight

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.dohia.androidbaseframework.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
Date: 2018/10/22
Time: 11:20
author: duhaitao
 */
class LoadingDialog(context: Context,var msg: String) : AlertDialog(context,R.style.loadingDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        tvMsg.text = msg
    }
}