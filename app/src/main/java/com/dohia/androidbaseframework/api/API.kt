package com.dohia.androidbaseframework.api

import com.dohia.androidbaseframework.model.Banner
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
Date: 2018/12/20
Time: 15:18
author: duhaitao
 */
interface API {
    @GET("")
    fun banner(): Call<Banner>

    companion object {
        private var BASEURL = ""
        fun create(): API {
            return Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(API::class.java)
        }
    }
}