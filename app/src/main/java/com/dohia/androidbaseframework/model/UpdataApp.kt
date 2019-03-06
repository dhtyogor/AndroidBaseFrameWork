package com.dohia.androidbaseframework.model

/**
Date: 2019/1/28
Time: 09:32
author: duhaitao
 */
data class UpdataApp (
        var result: ResultState,
        var data: UpdataAppData
)

data class UpdataAppData(
        var version_number: String,
        var update_type: String,
        var update_describe: String,
        var backage_url: String
)