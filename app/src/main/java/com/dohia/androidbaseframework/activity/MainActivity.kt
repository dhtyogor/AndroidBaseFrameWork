package com.dohia.androidbaseframework.activity

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {

    private var mCanExit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (KeyEvent.KEYCODE_BACK == keyCode ) {
            if (!mCanExit) {
                toast("在按一次退出")
                mCanExit = true
                Handler().postDelayed({mCanExit = false},2000)
            }
            moveTaskToBack(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
