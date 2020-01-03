package com.tunyin

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/7
 * desc: 基础Contract
 *
 */
interface BaseContract {

    interface BaseView {

        /**
         *  请求错误
         */
        fun showError(msg: String)

        fun complete()

        //显示进度
        fun showLoading()

        //隐藏进度
        fun hideLoading()

        //显示空页面
        fun showEmptyState()
    }

    interface BasePresenter<in T> {
        /**
         * 绑定
         *
         * @param view view
         */
        fun attachView(view: T)

        /**
         * 解绑
         */
        fun detachView()
    }
}