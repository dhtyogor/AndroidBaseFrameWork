package com.dohia.androidbaseframework.utils

import android.text.TextUtils
import android.util.Log

/**
Date: 2018/11/23
Time: 14:59
author: duhaitao
 */
class PatternHelper {

    private val MAX_SIZE = 4
    private val MAX_TIMES = 5
    private var message: String? = null
    private var tmpPwd: String? = null
    private var storagePwd: String? = null

    private var isFinish: Boolean = false
    private var isOk: Boolean = false
    private var times = 0

    private var  GESTURE_PWD_KEY = "gesture_pwd_key"


    fun validateForSetting(hitIndexList: List<Int>) {
        isOk = false
        isFinish =false
        if ((hitIndexList == null) or (hitIndexList.size < MAX_SIZE)) {
            this.tmpPwd = null
            this.message = getSizeErrorMsg()
            return
        }
        //1.draw first time
        if (TextUtils.isEmpty(tmpPwd)) {
            tmpPwd = convert2String(hitIndexList)
            message = getReDrawMsg()
            isOk = true
            return
        }
        Log.e("xxx","=="+hitIndexList+"<>>"+convert2String(hitIndexList))
        //2. draw second times
        if (tmpPwd == convert2String(hitIndexList)) {
            message = getSettingSuccessMsg()
            saveToStorage(tmpPwd!!)
            this.isOk = true
            this.isFinish = true
        } else {
            this.tmpPwd = null
            this.message = getDiffPreErrorMsg()
        }
    }

    fun validateForChecking(hitIndexList: List<Int>) {
        isOk = false
        if ((hitIndexList == null) or (hitIndexList.size < MAX_SIZE)) {
            times++
//            isFinish = times >= MAX_SIZE
            message = getPwdErrorMsg()
            return
        }
        storagePwd = getFromStorage()
        if (!TextUtils.isEmpty(storagePwd) and  (storagePwd == convert2String(hitIndexList))) {
            message = getCheckingSuccessMsg()
            isOk = true
            isFinish = true
        } else {
            times++
//            this.isFinish = this.times >= MAX_SIZE
            message = getPwdErrorMsg()
        }
    }

    fun getMessages() = message!!

    fun getIsFinish() = isFinish

    fun getIsOk() = isOk

    private fun getReDrawMsg() = "请再次绘制解锁图案"

    private fun getSettingSuccessMsg() = "手势解锁图案设置成功！"

    private fun getCheckingSuccessMsg() = "解锁成功！"

    private fun getSizeErrorMsg() = "${String.format("至少连接%d个点，请重新绘制", MAX_SIZE)}"

    private fun getDiffPreErrorMsg() = "与上次绘制不一致，请重新绘制"

    private fun getPwdErrorMsg() = "${String.format("密码错误，还剩%d次机会", getRemainTimes())}"

    private fun convert2String(hitIndexList: List<Int>) = hitIndexList.toString()

    private fun saveToStorage(gesturePwd: String) {
        SharedPreferencesUtil().putString(GESTURE_PWD_KEY,gesturePwd)
    }

    private fun getFromStorage() = SharedPreferencesUtil().getString(GESTURE_PWD_KEY)

    private fun getRemainTimes() = if (times < 5) MAX_TIMES - times else 0
}