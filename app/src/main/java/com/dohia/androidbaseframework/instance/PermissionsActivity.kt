package com.dohia.androidbaseframework.instance

import android.Manifest
import android.os.Bundle
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.mylhyl.acp.Acp
import com.mylhyl.acp.AcpOptions

class PermissionsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPermissions()
    }

    private fun setPermissions() {
        Acp.getInstance(this).request(AcpOptions.Builder().setPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WAKE_LOCK
        ).build(),null)
    }
}