package com.dohia.androidbaseframework.instance.aac.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dohia.androidbaseframework.instance.aac.model.NewsResult
import com.dohia.androidbaseframework.instance.aac.repository.Repository

/**
Date: 2018/12/6
Time: 11:06
author: duhaitao
 */
class AACViewModel(newsRepository: Repository) : ViewModel() {

    val data = MediatorLiveData<NewsResult>()
    init {
        data.addSource(newsRepository.getLastNews(),data::setValue)
    }
}