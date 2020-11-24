package com.tunyin.mvp.model

import android.content.Context
import com.tunyin.App
import com.tunyin.utils.AutoPreference

/**
 * 用户数据
 */
class SelfBean {

    companion object {
        @JvmStatic
        val instance = SelfBean()
    }

    // 李老师看这里：by AutoPreference这种的，赋值的时候是把值存到SharedPreferences，取的时候是从SP里面拿
//    var phone: String by AutoPreference("phone", "")
    var nickname by AutoPreference("nickname", "")
    var gender by AutoPreference("gender", "")
    var region by AutoPreference("region", "")
    var latitude by AutoPreference("latitude", "")
    var longitude by AutoPreference("longitude", "")
    var city by AutoPreference("city", "")
    var address by AutoPreference("address", "")
    var sign by AutoPreference("sign", "")
    var headImg by AutoPreference("headImg", "")
    var shopId by AutoPreference("shopId", "")
    var mobile by AutoPreference("mobile", "")
    var teamId by AutoPreference("teamId", "0")
    var invitationCode by AutoPreference("invitationCode", "0")


    var token by AutoPreference("token", "")
    var userId by AutoPreference("userId", "")
    var username by AutoPreference("username", "")
    var nickName by AutoPreference("nickName", "")
    var headUrl by AutoPreference("headUrl", "")
    var phone by AutoPreference("phone", "")
    var isEnter by AutoPreference("isEnter", false)
    var musicHisId by AutoPreference("musicHisId", "1")
    var musicUrl by AutoPreference("musicUrl", "")

    var messageNotice by AutoPreference("messageNotice", "1")
    var sex by AutoPreference("sex", "未填写")
    var birthday by AutoPreference("birthday", "未填写")
    var uId by AutoPreference("uid", "")

    // 普通的
    var something: Boolean = true
    var other: Boolean = true

    fun clear() {
        val sp = App.instance.applicationContext.getSharedPreferences("user", Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }
}