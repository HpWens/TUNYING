package com.tunyin.utils

import com.tunyin.utils.AppUtils
import com.tunyin.utils.FileUtils

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/14
 * desc:
 *
 */
object JsonUtils {
    fun readJson(jsonFile: String): String {
        val inputStream = FileUtils.openAssetFile(AppUtils.getAppContext(), jsonFile)
        return IOUtils.streamToString(inputStream!!)
    }
}
