package com.dohia.androidbaseframework.instance

import android.os.Bundle
import android.util.Log
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.activity.WebViewActivity
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.aac.activity.AACActivity
import com.dohia.androidbaseframework.instance.pattern.PatternActivity
import com.dohia.androidbaseframework.utils.DeviceUtil
import kotlinx.android.synthetic.main.instance_activity_instance_main.*
import org.jetbrains.anko.startActivity

class InstanceMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_instance_main)
        initView()
        initData()
    }

    private fun initView() {
        btnToolbar.setOnClickListener { startActivity<ToolbarActivity>() }
        btnPermissions.setOnClickListener { startActivity<PermissionsActivity>() }
        btnUpdateApp.setOnClickListener { startActivity<UpdateAppActivity>() }
        btnCamer.setOnClickListener { startActivity<TakePhotoActivity>() }
        btnVideo.setOnClickListener { startActivity<CustemVideoActivity>() }
        btnWebView.setOnClickListener { startActivity<WebViewActivity>() }
        btnTimer.setOnClickListener { startActivity<TimerActivity>() }
        btnLayout.setOnClickListener { startActivity<LayoutOptimizationActivity>() }
        btnPattern.setOnClickListener { startActivity<PatternActivity>() }
        btnContact.setOnClickListener { startActivity<ContactActivity>() }
        btnAAC.setOnClickListener { startActivity<AACActivity>() }
    }

    private fun initData() {
        Log.e("xxx","<手机厂商>${DeviceUtil().getDeviceBrand()}")
        Log.e("xxx","<手机型号>${DeviceUtil().getDeviceModel()}")
//        Log.e("xxx","<设备的唯一标识>${DeviceUtil().getDeviceId()}")
        Log.e("xxx","<手机系统版本号>${DeviceUtil().getDeviceVersion()}")
    }

}