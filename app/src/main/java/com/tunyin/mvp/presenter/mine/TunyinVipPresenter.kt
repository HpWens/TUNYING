package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.TunyinVipContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.mine.TunyinVipEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class TunyinVipPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<TunyinVipContract.View>(), TunyinVipContract.Presenter<TunyinVipContract.View> {

    override fun payTunyinVip(nobleEquityId: String, type: String) {
        val subscriber = mRetrofitHelper.payTunyinVip(nobleEquityId, type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.payTunyinCallback(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getTunyinVip() {
        val subscriber = mRetrofitHelper.tunyinVip()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<TunyinVipEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<TunyinVipEntity>) {
                        mView?.getTunyinVipCallBack(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

}