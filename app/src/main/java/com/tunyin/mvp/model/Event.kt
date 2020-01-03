package com.tunyin.mvp.model

/**
 * create by wangrongchao
 * on 2019/12/11
 */
class Event<T> {
    var code: Int = 0
    var data: T? = null

    constructor(code: Int) {
        this.code = code
    }

    constructor(code: Int, data: T) {
        this.code = code
        this.data = data
    }
}
