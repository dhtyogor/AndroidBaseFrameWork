package com.dohia.androidbaseframework.utils

import com.dohia.androidbaseframework.config.MyApp

/**
Date: 2018/11/23
Time: 17:22
author: duhaitao
 */
class SharedPreferencesUtil {

    fun putString(key: String, value:String): Boolean {
        val editor = MyApp.instance.getSharedPreferences(MyApp.instance.packageName,0).edit()//获取SharedPreferences.Editor引用对象
        editor.putString(key,value)
        return editor.commit()
    }

    fun getString(key: String): String {

        return MyApp.instance.getSharedPreferences(MyApp.instance.packageName,0).getString(key,"")
    }
}