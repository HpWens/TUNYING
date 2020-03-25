package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract

class ResetPasswordContract {
    interface View : BaseContract.BaseView {
        fun resetPassword(string: String)
        fun sendMsmSuc(string: String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun resetPsd(phone: String, password: String, code: String)
        fun sendMsm(phone: String, type: String)
    }
}