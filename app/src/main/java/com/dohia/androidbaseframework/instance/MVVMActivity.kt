package com.dohia.androidbaseframework.instance

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.databinding.InstanceActivityMvvmBinding
import com.dohia.androidbaseframework.instance.model.MVVMModel
import com.dohia.androidbaseframework.instance.viewmodel.MVVMViewModel

/**
Date: 2018/12/3
Time: 17:01
author: duhaitao
 */
class MVVMActivity : BaseActivity() {

    lateinit var mDataBinding: InstanceActivityMvvmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding =  DataBindingUtil.setContentView(this, R.layout.instance_activity_mvvm)
        MVVMModel().getData()
        var viewModel = MVVMViewModel().getData()

    }
}