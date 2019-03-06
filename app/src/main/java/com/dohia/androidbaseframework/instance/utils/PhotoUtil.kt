package com.dohia.androidbaseframework.instance.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile

/**
Date: 2019/1/3
Time: 15:41
author: duhaitao
 */
class PhotoUtil(context: Context, activity: AppCompatActivity) {

    var context: Context = context
    var activity: AppCompatActivity = activity

    companion object {
        var photoPath: String? = null
        const val REQUEST_CODE_TAKE_PHOTO = 0
        const val REQUEST_CODE_TAKE_VIDEO = 1
    }

    fun getTakePhoto() {
        var photFile = getPhotopath()
        photoPath = photFile.absolutePath

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra("android.intent.extras.CAMERA_FACING", 0)//前置摄像头
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            var authority = context.packageName + ".fileProvider"
            var contentUri = FileProvider.getUriForFile(context,authority,photFile)
            context.grantUriPermission(context.packageName,contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,contentUri)
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photFile))
        }
        activity.startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO)
    }

    fun getVideo() {
        var photoFile = getVideoPath()
        photoPath = photoFile.absolutePath

        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0)//设置视频录制质量1：高质量(mp4) 0：低质量(3gpp)
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5)//设置视频最大允许录制的时长
//        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024*1024L)//设置获取视频文件的大小
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//Android7.0以上的方法
            var authority = activity?.applicationContext.packageName + ".fileProvider"
            var contentUri = getUriForFile(activity,authority,photoFile)
            activity?.grantUriPermission(activity.packageName,contentUri,Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,contentUri)//设置系统相机拍摄照片完成图片文件的存放地址
        } else {//Android7.0以下的方法
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photoFile))
        }
        activity.startActivityForResult(intent, REQUEST_CODE_TAKE_VIDEO)
    }

    private fun getPhotopath(): File {
        val appDir = File(Environment.getExternalStorageDirectory(),"webview-h5-photo")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        return File(appDir,"${System.currentTimeMillis()}.jpg")
    }

    private fun getVideoPath(): File {
        val appDir = File(Environment.getExternalStorageDirectory(),"webview-h5-video")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        return File(appDir,"${System.currentTimeMillis()}.mp4")
    }

}