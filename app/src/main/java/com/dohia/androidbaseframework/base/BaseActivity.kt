package com.dohia.androidbaseframework.base

import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import android.widget.TextView
import com.dohia.androidbaseframework.broadcast.NetworkChangeReceiver
import com.dohia.androidbaseframework.weight.LoadingDialog

open class BaseActivity : AppCompatActivity() {

    private lateinit var mLoadingDialog: LoadingDialog
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoadingDialog = LoadingDialog(this,"加载中...")
        mLoadingDialog.setCanceledOnTouchOutside(false)
        netWorkCheck()
    }

    fun initSystemBarTint(barTint: Int) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = barTint
    }

    fun initToolBar(toolbar: Toolbar, tvTitle: TextView, title: String, icon: Int) {
        toolbar.title = ""
        tvTitle.text = title
        toolbar.setNavigationIcon(icon)
        setSupportActionBar(toolbar)
    }

    fun showLoadingDialog() {
        mLoadingDialog.show()
    }

    fun dismissLoadingDialog() {
        mLoadingDialog.dismiss()
    }

    private fun netWorkCheck() {
        var intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

}