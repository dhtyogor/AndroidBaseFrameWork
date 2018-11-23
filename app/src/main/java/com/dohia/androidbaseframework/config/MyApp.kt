package com.dohia.androidbaseframework.config

import android.app.Application
import android.content.Context
import android.util.Log
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpHeaders
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.entity.UMessage
import okhttp3.OkHttpClient
import org.android.agoo.xiaomi.MiPushRegistar
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
        //小米通道
        MiPushRegistar.register(this, "2882303761517896477", "5781789688477")
        //华为通道
        //HuaWeiRegister.register(this)
        //魅族通道
        //MeizuRegister.register(this, MEIZU_APPID, MEIZU_APPKEY)
        initUMPush()
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

    private fun initUMPush() {
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE,"e9788bbccc904cbd91e4e1a269464e78")
        val mPushAgent = PushAgent.getInstance(this)
        mPushAgent.displayNotificationNumber = 0
        //注册推送服务
        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String?) {
                Log.e("xxx","==deviceToken==$deviceToken")
            }

            override fun onFailure(p0: String?, p1: String?) {
                Log.e("xxx","==p0==$p0==p1==$p1")
            }
        })

        val notificationClickHandler = object : UmengNotificationClickHandler() {
            override fun dealWithCustomAction(context: Context?, msg: UMessage?) {
                super.dealWithCustomAction(context, msg)
            }
        }
        mPushAgent.notificationClickHandler = notificationClickHandler

    }

}