package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract

class ResetPasswordContract {
    interface View : BaseContract.BaseView {
        fun changePasswordSus(string: String)
        fun sendMsmSuc(string: String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun changePassword(password: String, code: String)
        fun sendMsm(phone: String, type: String)
    }
}