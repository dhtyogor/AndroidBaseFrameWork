package com.dohia.androidbaseframework.instance

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.activity.WebViewActivity
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.pattern.PatternActivity
import kotlinx.android.synthetic.main.instance_activity_instance_main.*
import org.jetbrains.anko.startActivity

class InstanceMainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_instance_main)
        initView()
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
    }

}