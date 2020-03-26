package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.UserBean
import com.tunyin.mvp.model.mine.VersionEntity

class SettingContract {

    interface View : BaseContract.BaseView {
        override fun showError(msg: String)
        fun fillingUserInfo(user: UserBean)
        fun checkUpdate(version: VersionEntity)
        fun updateUserInfoSuc(response: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getUserInfo()

        fun getVersionInfo()

        fun updateUserInfo(headUrl: String, nick: String, sex: String, bir: String, messageNotice: String)
    }

}