package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.ToastUtils
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.BaseSubscriberPro
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.LoginContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.mine.LoginEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<LoginContract.View>(), LoginContract.Presenter<LoginContract.View> {
    override fun login(phone: String, password: String) {
        val subscriber = mRetrofitHelper.login(phone, password)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<LoginEntity>(mView) {

                    override fun onSuccess(mData: LoginEntity) {
//                        if (mData.code=="400"){
//                            ToastUtils.showToast(mData.desc)
//                        }else{
                        mView?.showLoginData(mData)
//                        }
                    }

//
//                    override fun onError(t: Throwable?) {
//                        t!!.message?.let { mView?.showError(it) }
//                    }


                })
        addSubscribe(subscriber)
    }


}