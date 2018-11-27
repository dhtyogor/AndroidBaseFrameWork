package com.dohia.androidbaseframework.instance.pattern

import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.pattern.def.DefaultStyleActivity
import kotlinx.android.synthetic.main.instance_activity_pattern.*
import org.jetbrains.anko.startActivity

/**
Date: 2018/11/23
Time: 14:17
author: duhaitao
 */
class PatternActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_pattern)
        initView()
    }

    private fun initView() {
        btnPatternDef.setOnClickListener { startActivity<DefaultStyleActivity>() }
    }

}