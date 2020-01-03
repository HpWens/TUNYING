package com.tunyin.mvp.model.mine


data class LoginBean(val code: String, val `content`: Content, val desc: String) {

    data class Content(
            val token: String,
            val userId: String,
            val username: String,
            val phone: String,
            val loginTime:String,
            val expiresTime:String) {

    }
}