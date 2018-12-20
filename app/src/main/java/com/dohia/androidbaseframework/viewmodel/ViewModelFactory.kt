package com.dohia.androidbaseframework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dohia.androidbaseframework.repository.Repository

/**
Date: 2018/12/20
Time: 15:30
author: duhaitao
 */
class ViewModelFactory(private val rep: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(rep) as T
    }
}