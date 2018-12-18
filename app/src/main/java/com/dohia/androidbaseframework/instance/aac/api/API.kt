package com.dohia.androidbaseframework.instance.aac.api

import com.dohia.androidbaseframework.instance.aac.model.NewsResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
Date: 2018/12/17
Time: 15:02
author: duhaitao
 */
interface API {
    
    @GET("news/latest")
   fun lastNews(): Call<NewsResult>


    companion object {
        private val BASEURL = "https://news-at.zhihu.com/api/4/"

        fun create() : API {
            return Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(API::class.java)
        }
    }
}