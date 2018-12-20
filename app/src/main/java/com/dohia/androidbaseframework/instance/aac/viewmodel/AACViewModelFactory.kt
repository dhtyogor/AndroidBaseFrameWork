package com.dohia.androidbaseframework.instance.aac.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dohia.androidbaseframework.instance.aac.repository.Repository

/**
Date: 2018/12/7
Time: 15:12
author: duhaitao
 */
class AACViewModelFactory(val np: Repository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AACViewModel(np) as T
    }
}