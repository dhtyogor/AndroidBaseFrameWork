package com.dohia.androidbaseframework.instance.aac.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohia.androidbaseframework.config.MyApp
import com.dohia.androidbaseframework.instance.aac.api.API
import com.dohia.androidbaseframework.instance.aac.model.NewsResult
import retrofit2.Call
import org.jetbrains.anko.toast

/**
Date: 2018/12/6
Time: 16:32
author: duhaitao
 */
class Repository(val api: API) {

     fun getLastNews(): LiveData<NewsResult> {
        val data: MutableLiveData<NewsResult> = MutableLiveData()
        api.lastNews().enqueue(resultFactory<NewsResult> {
            data.value = it
        })
        return data
    }


    private fun <T> resultFactory(action: (T?) -> Unit): retrofit2.Callback<T> {
        return object : retrofit2.Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                action(response.body())
                MyApp.instance.toast("数据获取成功")
            }
            override fun onFailure(call: Call<T>, t: Throwable) {}
        }
    }

    companion object {
        private var instance: Repository? = null

        fun getInstance(api: API) = instance ?: synchronized(this) {
            instance ?: Repository(api).also { instance = it }
         }
    }

}