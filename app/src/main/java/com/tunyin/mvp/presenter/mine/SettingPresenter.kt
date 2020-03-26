package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.SettingContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UserBean
import com.tunyin.mvp.model.mine.VersionEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class SettingPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<SettingContract.View>(), SettingContract.Presenter<SettingContract.View> {

    override fun updateUserInfo(headUrl: String, nick: String, sex: String, bir: String, messageNotice: String) {
        val subscriber = mRetrofitHelper.updateUserInfo(headUrl, nick, sex, bir, messageNotice)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.updateUserInfoSuc(mData.content)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        mView?.updateUserInfoSuc("")
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getUserInfo() {
        val subscriber = mRetrofitHelper.getUserInfo()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<UserBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<UserBean>) {
                        mView?.fillingUserInfo(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getVersionInfo() {
        val subscriber = mRetrofitHelper.getVersion()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<VersionEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<VersionEntity>) {
                        mView?.checkUpdate(mData.content)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
        addSubscribe(subscriber)
    }
}