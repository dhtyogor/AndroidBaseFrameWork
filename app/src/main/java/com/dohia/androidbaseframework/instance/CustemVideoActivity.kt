package com.dohia.androidbaseframework.instance

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import camerplugin.zsoftware.com.videoz.SmallVideoActivity
import camerplugin.zsoftware.com.videoz.entry.VideoResult
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import kotlinx.android.synthetic.main.instance_activity_video.*

class CustemVideoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_video)
        initView()
    }

    private fun initView() {
        btnVideo.setOnClickListener { startVideo() }
    }

    private fun startVideo() {
        val intent = Intent()
        intent.setClass(this, SmallVideoActivity::class.java)
        startActivityForResult(intent, SmallVideoActivity.RECORDER_RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            SmallVideoActivity.RECORDER_RESULT_CODE -> {
                if (data == null) {
                    return
                }
                if (resultCode == 0) {
                    val videoResult = data.getSerializableExtra(SmallVideoActivity.RECORDER_CONFIG_KEY) as VideoResult
                    Toast.makeText(this, videoResult.videoPath + " 文件大小:" + videoResult.fileSizeDesc, Toast.LENGTH_LONG).show()
                    Log.e("xxx", "===path===" + videoResult.videoPath)
                }
            }
        }
    }

}