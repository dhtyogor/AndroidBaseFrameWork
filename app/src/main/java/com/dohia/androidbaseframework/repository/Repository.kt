package com.dohia.androidbaseframework.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohia.androidbaseframework.api.API
import com.dohia.androidbaseframework.model.Banner
import retrofit2.Call

/**
Date: 2018/12/20
Time: 15:23
author: duhaitao
 */
class Repository(val api: API) {

    fun getBannerData(): LiveData<Banner> {
        var data: MutableLiveData<Banner> = MutableLiveData()
        api.banner().enqueue(resultFactory {
            data.value = it
        })
        return data
    }

    private fun <T> resultFactory(action: (T?) -> Unit): retrofit2.Callback<T> {
        return object : retrofit2.Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                action(response.body())
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