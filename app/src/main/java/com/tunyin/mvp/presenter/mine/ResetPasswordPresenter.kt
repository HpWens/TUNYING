package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.ResetPasswordContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class ResetPasswordPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<ResetPasswordContract.View>(), ResetPasswordContract.Presenter<ResetPasswordContract.View> {
    override fun resetPsd(phone: String, password: String, code: String) {
        val subscriber = mRetrofitHelper.forgetPwd(phone, password, code)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.resetPassword(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun sendMsm(phone: String, type: String) {
        val subscriber = mRetrofitHelper.sendMsm(phone, type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.sendMsmSuc(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }
}
