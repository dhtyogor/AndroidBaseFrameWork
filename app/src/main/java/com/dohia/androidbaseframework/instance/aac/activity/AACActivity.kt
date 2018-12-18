package com.dohia.androidbaseframework.instance.aac.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.instance.aac.api.API
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.aac.repository.NewsRepository
import com.dohia.androidbaseframework.instance.aac.viewmodel.AACViewModel
import com.dohia.androidbaseframework.instance.aac.viewmodel.AACViewModelFactory

/**
Date: 2018/12/6
Time: 10:51
author: duhaitao
 */
class AACActivity : BaseActivity() {

    private lateinit var viewModel: AACViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_aac)
        initView()
        initData()
    }

    private fun initView() {}

    private fun initData() {
        viewModel = ViewModelProviders.of(this, AACViewModelFactory(NewsRepository.getInstance(API.create()))).get(AACViewModel::class.java)

        viewModel.news.observe(this, Observer {

        })
    }

}