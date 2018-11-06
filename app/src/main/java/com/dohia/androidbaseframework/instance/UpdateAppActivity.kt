package com.dohia.androidbaseframework.instance

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.http.OkGoUpdateHttpUtil
import com.vector.update_app.UpdateAppManager

class UpdateAppActivity : BaseActivity() {

    //    var mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt"
    var mUpdateUrl = "https://jr.huanqiu.com/api/financial/client_update_version?device_type=ANDROID"
    //http://showdoc.dev.congred.com/web/?#/67

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAPP()
    }

    private fun updateAPP() {
        /**
         * "constraint": true 强制更新
         * "constraint": false不强制更新
         * */
        UpdateAppManager.Builder().setActivity(this)
                .setUpdateUrl(mUpdateUrl)//更新地址
                .setHttpManager(OkGoUpdateHttpUtil())//实现httpManager接口的对象
                .setThemeColor(ContextCompat.getColor(this,R.color.colorPrimaryDark))//按钮进度条颜色
                .build()
                .update()
    }

}