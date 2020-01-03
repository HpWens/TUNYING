package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.contract.mine.ForgetPwdContract
import com.tunyin.mvp.contract.mine.RegisterContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.RegisterEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class ForgetPwdPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<ForgetPwdContract.View>(), ForgetPwdContract.Presenter<ForgetPwdContract.View> {
    override fun forgetPwd(phone: String, password: String, code: String) {
        val subscriber = mRetrofitHelper.forgetPwd(phone, password, code)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.forgetPwdData(mData.content)
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