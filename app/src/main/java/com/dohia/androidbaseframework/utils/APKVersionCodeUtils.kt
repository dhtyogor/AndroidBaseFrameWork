package com.dohia.androidbaseframework.utils

import android.content.pm.PackageManager
import com.dohia.androidbaseframework.config.MyApp

/**
Date: 2018/10/18
Time: 10:47
author: duhaitao
 */
class APKVersionCodeUtils {

    companion object {
        /**
         * 获取当前本地apk的版本
         * @return
         */
        fun getVersionCode(): Int {
            var versionCode = 0
            try {
                //获取软件版本号，对应AndroidManifest.xml下android:versionCode
                versionCode = MyApp.instance.packageManager.getPackageInfo(MyApp.instance.packageName,0).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return versionCode
        }
        /**
         * 获取版本号名称
         * @return
         */
        fun getVersionName(): String {
            var versionName = ""
            try {
                versionName = MyApp.instance.packageManager.getPackageInfo(MyApp.instance.packageName,0).versionName
            } catch (e: PackageManager.NameNotFoundException) {}
            return versionName
        }

    }

}