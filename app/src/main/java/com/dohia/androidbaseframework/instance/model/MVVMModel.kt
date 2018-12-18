package com.dohia.androidbaseframework.instance.model

import android.util.Log
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import org.json.JSONObject

/**
Date: 2018/12/3
Time: 17:13
author: duhaitao
 */
class MVVMModel {

    fun getData() {
        var json = JSONObject()
        json.put("account", "18719818127")
        json.put("password", "123456")
        json.put("lon", "123")
        json.put("lat", "456")
        OkGo.post<String>("http://arcs-test.congred.com/api/broker/new_login").upJson(json).execute(object : StringCallback() {
            override fun onSuccess(response: Response<String>) {
                var jsonObject = JSONObject(response.body())
                var status = jsonObject.getInt("status")
                Log.e("xxx","<=>"+status)
                MVVMBean(status)
            }
        })
    }
}