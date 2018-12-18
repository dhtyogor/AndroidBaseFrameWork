package com.dohia.androidbaseframework.base

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.WindowManager
import android.widget.TextView
import com.dohia.androidbaseframework.broadcast.NetworkStateReceiver
import com.dohia.androidbaseframework.weight.LoadingDialog
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

open class BaseActivity : AppCompatActivity(),NetworkStateReceiver.setBatteryText {

    private lateinit var mLoadingDialog: LoadingDialog
    private lateinit var networkChangeReceiver: NetworkStateReceiver

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
        networkChangeReceiver = NetworkStateReceiver()
        registerReceiver(networkChangeReceiver,intentFilter)
        networkChangeReceiver.getBatteryText(this)
    }

    override fun setText(isNet: Boolean) {
        if (!isNet) {
            setNetworkMethod()
        }
    }


    private fun setNetworkMethod() {
        alert ("网络连接不可用,是否进行设置?","网络设置提示"){
            okButton {
                var intent = Intent(android.provider.Settings.ACTION_SETTINGS)
                startActivity(intent)
            }
            cancelButton { it.dismiss() }
        }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

}