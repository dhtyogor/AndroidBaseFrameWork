package com.dohia.androidbaseframework.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dohia.androidbaseframework.model.Banner
import com.dohia.androidbaseframework.repository.Repository

/**
Date: 2018/12/20
Time: 15:26
author: duhaitao
 */
class DataViewModel(rep: Repository) : ViewModel() {
    var banner = MediatorLiveData<Banner>()
    init {
        banner.addSource(rep.getBannerData(),banner::setValue)
    }
}