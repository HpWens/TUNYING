package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.ToastUtils
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.mine.LoginEntity

class LoginContract {

    interface View : BaseContract.BaseView {
        fun showLoginData(loginEntity: LoginEntity)
        override fun showError(msg: String){
            ToastUtils.showToast(msg)
        }
//        fun showErrorTips(msg: String, hasTry: Boolean)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun login(phone:String,password:String)
    }
}