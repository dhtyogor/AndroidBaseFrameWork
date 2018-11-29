package com.dohia.androidbaseframework.instance

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import com.dohia.androidbaseframework.R
import com.dohia.androidbaseframework.base.BaseActivity
import com.dohia.androidbaseframework.instance.bean.Contact
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList
import java.util.regex.Pattern


/**
Date: 2018/11/29
Time: 11:29
author: duhaitao
 */
class ContactActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instance_activity_contact)
        queryContactPhoneNumber()
    }


    private fun queryContactPhoneNumber() {
        var json = JSONObject()
        val cols = arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, cols, null, null, null)
        for (i in 0 until cursor!!.count) {
            cursor.moveToPosition(i)
            // 取得联系人名字
            val nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)
            val numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val name = cursor.getString(nameFieldColumnIndex)
            val number = cursor.getString(numberFieldColumnIndex)
            var phone = formatPhoneNum(number)
            json.put(phone,name)

        }
        Log.e("xxx","==>>"+json)
    }

    private fun formatPhoneNum(phoneNum: String): String? {
        var regex = "(\\+86)|[^0-9]"
        var  pattern = Pattern.compile(regex)
        var matcher = pattern.matcher(phoneNum)
        return matcher.replaceAll("")
    }

}