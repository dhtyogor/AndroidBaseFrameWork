package com.dohia.androidbaseframework.activity

import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.*
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*
import org.jetbrains.anko.act

/**
Date: 2018/10/19
Time: 14:00
author: duhaitao
 */
class WebViewActivity : BaseActivity(){

    private val url = "file:///android_asset/uploadImg.html"
    var mFilePathCallback: ValueCallback<Array<Uri>>? = null

    //https://test.yaoyongqian.com/h5/site
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

    }

    override fun onResume() {
        super.onResume()
        initWebView()
        webView.loadUrl(url)
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

        ///
        webSettings.allowContentAccess = true  // 是否可访问Content Provider的资源，默认值 true
        webSettings.allowFileAccess = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.domStorageEnabled = true

        webSettings.setDefaultTextEncodingName("UTF-8")

        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        webSettings.setAllowFileAccessFromFileURLs(false)
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        webSettings.setAllowUniversalAccessFromFileURLs(false)
        //开启JavaScript支持
        webSettings.setJavaScriptEnabled(true)

    }

    private var webChromeClient: WebChromeClient = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                numberProgress.visibility = View.GONE
            } else {
                numberProgress.visibility = View.VISIBLE
                numberProgress.progress = newProgress
            }
        }

        override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
            super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            mFilePathCallback = filePathCallback
            var accept = fileChooserParams?.acceptTypes
            for(i in accept!!){
                if (i == "image/*") {
                    openCarcme() //调用系统相机拍照
                }
                if (i == "video/*") {
                    openVideo()//调用系统相机录像
                }
            }

            return true
        }

    }

    private var webViewClient: WebViewClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return true
        }
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            handler?.proceed() //接受所有网站的证书
        }
    }

    @JavascriptInterface
    fun showInfoFromJs() {}


    private fun openCarcme() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 1)
    }

    private fun openVideo() {
        var intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent, 1)
    }


}