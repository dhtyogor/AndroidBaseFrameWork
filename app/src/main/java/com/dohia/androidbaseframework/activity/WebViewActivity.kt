package com.dohia.androidbaseframework.activity

import android.net.http.SslError
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initWebView()
        webView.loadUrl("https://www.baidu.com")
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

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                numberProgress.visibility = View.GONE
            } else {
                numberProgress.visibility = View.VISIBLE
                numberProgress.progress = newProgress
            }
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

}