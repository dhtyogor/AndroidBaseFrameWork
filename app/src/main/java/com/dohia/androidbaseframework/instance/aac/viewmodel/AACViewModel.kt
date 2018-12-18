package com.dohia.androidbaseframework.instance.aac.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dohia.androidbaseframework.instance.aac.model.NewsResult
import com.dohia.androidbaseframework.instance.aac.repository.NewsRepository

/**
Date: 2018/12/6
Time: 11:06
author: duhaitao
 */
class AACViewModel(newsRepository: NewsRepository) : ViewModel() {

    val news = MediatorLiveData<NewsResult>()
    init {
        news.addSource(newsRepository.getLastNews(),news::setValue)
    }
}