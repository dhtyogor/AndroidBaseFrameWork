package com.dohia.androidbaseframework.instance.pattern.def

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import kotlinx.android.synthetic.main.instance_activity_pattern_default_style.*
import org.jetbrains.anko.startActivity

/**
Date: 2018/11/23
Time: 14:24
author: duhaitao
 */
class DefaultStyleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_pattern_default_style)
        initView()
    }

    private fun initView() {
        btnDefaultPatternSetting.setOnClickListener { startActivity<DefaultPatternSettingActivity>() }
        btnDefaultPatternChecking.setOnClickListener { startActivity<DefaultPatternCheckingActivity>() }
    }

}