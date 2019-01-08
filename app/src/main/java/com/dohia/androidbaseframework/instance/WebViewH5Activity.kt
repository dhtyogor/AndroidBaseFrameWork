package com.dohia.androidbaseframework.instance

import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.utils.PhotoUtil
import kotlinx.android.synthetic.main.activity_webview.*
import org.jetbrains.anko.act
import java.io.File

/**
Date: 2019/1/3
Time: 15:29
author: duhaitao
 */
class WebViewH5Activity : BaseActivity() {

    private val url = "file:///android_asset/uploadImg.html"
//    private val url = "https://test.yaoyongqian.com/h5/site"
    private lateinit var mPhotoUtil: PhotoUtil
    var mFilePathCallback: ValueCallback<Array<Uri>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initWebView()
        webView.loadUrl(url)
        mPhotoUtil = PhotoUtil(this,this@WebViewH5Activity)
    }

    private fun initWebView() {
        val webSettings = webView.settings
        //JS处理
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过js打开新窗口
        webSettings.domStorageEnabled = true
        //缩放处理
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true //缩放至屏幕的大小
        webSettings.setSupportZoom(true) //支持缩放，默认为true
        webSettings.builtInZoomControls = true //设置内置的缩放控件
        webSettings.displayZoomControls = false //隐藏原生的缩放控件
        //文件缓存
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.allowFileAccess = false

        webSettings.domStorageEnabled = true //开启dom storage api功能h5
        //字体大小设置
//        webSettings.defaultFontSize = 12
        webSettings.textSize = WebSettings.TextSize.NORMAL //设置字体大小不随系统字体大小改变

        webView.webChromeClient = webChromeClient
        webView.webViewClient = webViewClient


        webView.addJavascriptInterface(act,"JSToAndroid")
    }

    private var webChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
            super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            mFilePathCallback = filePathCallback
            var accept = fileChooserParams?.acceptTypes
            for(i in accept!!){
                if (i == "image/*") {
                    mPhotoUtil.getTakePhoto()
                }
                if (i == "video/*") {
                    mPhotoUtil.getVideo()
                }
            }
            return true
        }
    }

    private var webViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            handler?.proceed() //接受所有网站的证书
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PhotoUtil.REQUEST_CODE_TAKE_PHOTO -> {
                finishActivity(PhotoUtil.REQUEST_CODE_TAKE_PHOTO)
                var path = PhotoUtil?.photoPath
                var uri = Uri.fromFile(File(path))
                mFilePathCallback?.onReceiveValue(arrayOf(uri))
            }
            PhotoUtil.REQUEST_CODE_TAKE_VIDEO -> {
                finishActivity(PhotoUtil.REQUEST_CODE_TAKE_VIDEO)
                var path = PhotoUtil?.photoPath
                var uri = Uri.fromFile(File(path))
                mFilePathCallback?.onReceiveValue(arrayOf(uri))
            }
        }
    }

}