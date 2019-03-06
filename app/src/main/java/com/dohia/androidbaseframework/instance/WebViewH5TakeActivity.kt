package com.dohia.androidbaseframework.instance

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import androidx.appcompat.app.AppCompatActivity
import com.dohia.androidbaseframework.R
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.mylhyl.acp.Acp
import com.mylhyl.acp.AcpOptions
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlinx.android.synthetic.main.instance_activity_webview_h5_take.*
import java.io.File

/**
Date: 2019/2/28
Time: 15:45
author: duhaitao
 */
class WebViewH5TakeActivity : AppCompatActivity(), TakePhoto.TakeResultListener, InvokeListener {

    private var takePhoto: TakePhoto? = null
    private var invokeParam: InvokeParam? = null
    private var selType = ""

    private val url = "file:///android_asset/uploadtake.html"

    override fun onCreate(savedInstanceState: Bundle?) {
        getTakePhoto().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_webview_h5_take)
        webView.loadUrl(url)
        initWebView()
//        setPermissions()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        getTakePhoto().onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }

    /**
     * 获取TakePhoto实例
     * */
    fun getTakePhoto(): TakePhoto {
        if (takePhoto == null) {
            takePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
        }
        return takePhoto!!
    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam!!.method)
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }
    override fun takeSuccess(result: TResult?) {
        Log.e("xxx","=="+result?.image?.compressPath+"///"+result?.image?.originalPath)
        var bitmap = BitmapFactory.decodeFile(result?.image?.compressPath)
        var stringPath = bitmaptoString(bitmap!!)
        var json = JSONObject()
        json.put("type",selType)
        json.put("img","data:image/png;base64,$stringPath")
        sendInfoToJs(json)
    }

    override fun takeCancel() {}

    override fun takeFail(result: TResult?, msg: String?) { Log.e("xxx",">>"+msg) }

    private fun initWebView() {
        //1.设置WebView使其支持JS调用JAVA方法

        webView.settings.javaScriptCanOpenWindowsAutomatically = true //支持通过js打开新窗口

        ////设置WebView支持JS
        webView.settings.javaScriptEnabled = true

        webView.addJavascriptInterface(this@WebViewH5TakeActivity,"JSToAndroid")
    }
    @JavascriptInterface
    fun showInfoFromJs(type: Int) {
        Log.e("xxx", "==type== $type")
        when(type) {
            1 -> {
                selType = "before"

                Log.e("xxx","??"+getPhotopath())
                val imageUri = Uri.fromFile(getPhotopath())
                takePhoto?.onPickFromCapture(imageUri)
                configCompress()
            }
            2 -> {
                selType = "after"
                configCompress()
                val imageUri = Uri.fromFile(getPhotopath())
                takePhoto?.onPickFromCapture(imageUri)
            }
        }
    }

    /**
     * 压缩配置
     * setMaxSize 压缩大小
     * setMaxPixel 压缩长宽
     * */
    private fun configCompress() {
        var compressConfig = CompressConfig.Builder().setMaxSize(200*1024).setMaxPixel(1200).enableReserveRaw(false).create()
        takePhoto?.onEnableCompress(compressConfig,false)
    }

    fun bitmaptoString(bitmap: Bitmap): String {
        // 将Bitmap转换成Base64字符串
        val string = StringBuffer()
        val bStream = ByteArrayOutputStream()

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, bStream)
            bStream.flush()
            bStream.close()
            val bytes = bStream.toByteArray()
            string.append(Base64.encodeToString(bytes, Base64.NO_WRAP))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return string.toString()
    }

    private fun sendInfoToJs(path: JSONObject) {
        if (Build.VERSION.SDK_INT < 18) {
            webView.loadUrl("javascript:showInfoFromJava('$path')")
        } else {
            webView.evaluateJavascript("javascript:showInfoFromJava('$path')", ValueCallback {

            })
        }
    }

    private fun setPermissions() {
        Acp.getInstance(this).request(
                AcpOptions.Builder().setPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                Manifest.permission.CAMERA
                ).build(),null)
    }

    fun getPhotopath(): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)//外部存储私有目录
        return File(storageDir,"${System.currentTimeMillis()}.jpg")
    }

}