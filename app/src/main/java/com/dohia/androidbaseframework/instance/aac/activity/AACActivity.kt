package com.dohia.androidbaseframework.instance.aac.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.instance.aac.api.API
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.aac.adapter.AACAdapter
import com.dohia.androidbaseframework.instance.aac.model.News
import com.dohia.androidbaseframework.instance.aac.repository.Repository
import com.dohia.androidbaseframework.instance.aac.viewmodel.AACViewModel
import com.dohia.androidbaseframework.instance.aac.viewmodel.AACViewModelFactory
import kotlinx.android.synthetic.main.instance_activity_aac.*

/**
Date: 2018/12/6
Time: 10:51
author: duhaitao
 */
class AACActivity : BaseActivity() {

    private lateinit var viewModel: AACViewModel
    private val listData = arrayListOf<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_aac)
        initView()
        initData()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AACAdapter(listData)
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, AACViewModelFactory(Repository.getInstance(API.create()))).get(AACViewModel::class.java)
        viewModel.data.observe(this, Observer {
            listData.addAll(it.stories)
            recyclerView.adapter?.notifyDataSetChanged()
        })
    }

}