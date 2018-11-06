package com.dohia.androidbaseframework.config

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpHeaders
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class MyApp : Application() {

    companion object {
        // 单例不会是null   所以使用notNull委托
        var instance: MyApp by Delegates.notNull()

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initOKGO()
    }

    private fun initOKGO() {
        val headers = HttpHeaders()
        headers.put("User-Agent","")
        //构建OkHttpClient.Builder
        val builder = OkHttpClient.Builder()
        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)   //全局的连接超时时间

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .addCommonHeaders(headers)
    }

}