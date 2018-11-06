package com.dohia.androidbaseframework.http

import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Progress
import com.vector.update_app.HttpManager

import java.io.File

/**
 * 使用OkGo实现接口
 */

class OkGoUpdateHttpUtil : HttpManager {
    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param params   get参数
     * @param callBack 回调
     */
    override fun asyncGet(url: String, params: Map<String, String>, callBack: HttpManager.Callback) {
        OkGo.get<String>(url).params(params).execute(object : com.lzy.okgo.callback.StringCallback() {
            override fun onSuccess(response: com.lzy.okgo.model.Response<String>) {
                callBack.onResponse(response.body())
            }

            override fun onError(response: com.lzy.okgo.model.Response<String>) {
                super.onError(response)
                callBack.onError("异常")
            }
        })
    }

    /**
     * 异步post
     *
     * @param url      post请求地址
     * @param params   post请求参数
     * @param callBack 回调
     */
    override fun asyncPost(url: String, params: Map<String, String>, callBack: HttpManager.Callback) {
        OkGo.post<String>(url).params(params).execute(object : com.lzy.okgo.callback.StringCallback() {
            override fun onSuccess(response: com.lzy.okgo.model.Response<String>) {
                callBack.onResponse(response.body())
            }

            override fun onError(response: com.lzy.okgo.model.Response<String>) {
                super.onError(response)
                callBack.onError("异常")
            }
        })
    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    override fun download(url: String, path: String, fileName: String, callback: HttpManager.FileCallback) {
        OkGo.get<File>(url).execute(object : com.lzy.okgo.callback.FileCallback(path, fileName) {
            override fun onSuccess(response: com.lzy.okgo.model.Response<File>) {
                callback.onResponse(response.body())
            }

            override fun onStart(request: com.lzy.okgo.request.base.Request<File, out com.lzy.okgo.request.base.Request<*, *>>?) {
                super.onStart(request)
                callback.onBefore()
            }

            override fun onError(response: com.lzy.okgo.model.Response<File>) {
                super.onError(response)
                callback.onError("异常")
            }

            override fun downloadProgress(progress: Progress?) {
                super.downloadProgress(progress)

                callback.onProgress(progress!!.fraction, progress.totalSize)
            }
        })
    }
}