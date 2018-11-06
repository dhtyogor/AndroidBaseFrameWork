package com.dohia.androidbaseframework.instance

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bumptech.glide.Glide
import com.dohia.androidbaseframework.R
import com.jph.takephoto.app.TakePhotoActivity
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import kotlinx.android.synthetic.main.instance_activity_take_photo.*
import java.io.File

/**
Date: 2018/10/29
Time: 09:16
author: duhaitao
 */
 class TakePhotoActivity : TakePhotoActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_take_photo)
        initView()
    }

    private fun initView() {
        btnCamer.setOnClickListener {
            val file = File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg")
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            configCompress()
            val imageUri = Uri.fromFile(file)
            takePhoto.onPickFromCapture(imageUri)
        }
    }

    /**
     * 压缩配置
     * setMaxSize 压缩大小
     * setMaxPixel 压缩长宽
     * */
    private fun configCompress() {
        var compressConfig = CompressConfig.Builder().setMaxSize(100*1024).setMaxPixel(1200).enableReserveRaw(false).create()
        takePhoto.onEnableCompress(compressConfig,false)
    }

    override fun takeSuccess(result: TResult?) {
        super.takeSuccess(result)
        Log.e("xxx","==="+result?.image?.compressPath)
        Glide.with(this).load(result?.image?.compressPath).into(ivShowPhoto)
    }

     override fun takeFail(result: TResult?, msg: String?) {
         super.takeFail(result, msg)
         Log.e("xxx","==fail="+result.toString()+"//"+msg)
     }

     override fun takeCancel() {
         super.takeCancel()
         Log.e("xxx","xxxxxx")
     }
}