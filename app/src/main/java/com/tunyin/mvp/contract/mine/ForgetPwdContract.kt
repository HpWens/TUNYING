package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.RegisterEntity

/**
 * create by wangrongchao
 * on 2019/11/2
 *
 **/
class ForgetPwdContract {
    interface View : BaseContract.BaseView {
        fun forgetPwdData(string: String)
        fun sendMsmSuc(string: String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun forgetPwd(phone: String, password: String, code: String)
        fun sendMsm(phone: String, type: String)
    }
}