package com.dohia.androidbaseframework.instance

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class ToolbarActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_toolbar)
        initSystemBarTint(ContextCompat.getColor(this,R.color.colorAccent))
        initToolBar(toolbar,tvTitle,"Toolbar标题",R.drawable.ic_chevron_left_black_24dp)

        //Palette取色
        Palette.from(BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)).generate { palette ->
            var vibrant = palette!!.vibrantSwatch
            var rgb = vibrant!!.rgb
            window.statusBarColor = rgb
        }
    }







}