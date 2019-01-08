package com.dohia.androidbaseframework.instance.aac.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.instance.aac.model.News

/**
Date: 2018/12/19
Time: 11:53
author: duhaitao
 */
class AACAdapter(data: List<News>) : BaseQuickAdapter<News,BaseViewHolder>(R.layout.instance_adapter_aac,data) {
    override fun convert(helper: BaseViewHolder?, item: News?) {
        helper?.setText(R.id.tvTitle,item?.title)
        Log.e("xxx",item?.title)
    }
}