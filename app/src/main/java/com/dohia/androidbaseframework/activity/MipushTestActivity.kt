package com.dohia.androidbaseframework.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.dohia.androidbaseframework.R
import com.umeng.message.UmengNotifyClickActivity
import org.android.agoo.common.AgooConstants

/**
Date: 2018/11/22
Time: 15:38
author: duhaitao
 */
class MipushTestActivity : UmengNotifyClickActivity() {

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        setContentView(R.layout.activity_webview)
    }

    override fun onMessage(p0: Intent?) {
        super.onMessage(p0)
        var body = intent.getStringExtra(AgooConstants.MESSAGE_BODY)
        Log.e("xxx","=body=${body}")

    }
}