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
class RegisterContract {
    interface View : BaseContract.BaseView {
        fun registerData(registerSuccess: Boolean, registerEntity: RegisterEntity)
        fun sendMsmSuc(string: String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun register(phone: String, password: String, nickName: String, code: String)
        fun sendMsm(phone: String, type: String)
    }
}