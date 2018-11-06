package com.dohia.androidbaseframework.instance

import android.os.Bundle
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import kotlinx.android.synthetic.main.instance_activity_guide.*
import org.jetbrains.anko.startActivity

class GuideActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_guide)
        bannerGuide.setEnterSkipViewIdAndDelegate(R.id.btnMain,R.id.tvSkip) {
            startActivity<InstanceMainActivity>()
        }
        var localImageSize = BGALocalImageSize(1080,1920,320F,640F)
        bannerGuide.setData(localImageSize,ImageView.ScaleType.CENTER_CROP,R.mipmap.ic_launcher,R.mipmap.ic_launcher)
    }
}