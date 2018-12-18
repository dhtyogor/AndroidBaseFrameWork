package com.dohia.androidbaseframework.instance.viewmodel

import androidx.lifecycle.ViewModel
import com.dohia.androidbaseframework.instance.model.MVVMModel

/**
Date: 2018/12/3
Time: 17:05
author: duhaitao
 */
class MVVMViewModel {

    fun getData() {
        MVVMModel().getData()
    }
}