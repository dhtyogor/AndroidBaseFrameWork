package com.dohia.androidbaseframework.base

import android.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.WindowManager
import android.widget.TextView
import com.dohia.androidbaseframework.weight.LoadingDialog

class BaseFragment : Fragment() {

    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(activity,"加载中...")
        loadingDialog.setCanceledOnTouchOutside(false)
    }

    fun initSystemBarTint(barTint: Int) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity.window.statusBarColor = barTint
    }

    fun initToolBar(toolbar: Toolbar, tvTitle: TextView, title: String, icon: Int) {
        toolbar.title = ""
        tvTitle.text = title
        toolbar.setNavigationIcon(icon)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    fun showLoadingDialog() {
        loadingDialog.show()
    }

    fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }

}