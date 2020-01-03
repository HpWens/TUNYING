package com.tunyin.mvp.presenter.discovery

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class DiscoveryPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<DiscoveryContract.View>(), DiscoveryContract.Presenter<DiscoveryContract.View> {
    override fun refreshThemeSleep(noteClassId: String) {
        val subscriber = mRetrofitHelper.refreshDisThemeSleep(noteClassId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<DiscoveryEntity.ThemeSleepBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<DiscoveryEntity.ThemeSleepBean>) {
                        mView?.showRefreshThemeSleep(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshRadio(noteClassId: String) {
        val subscriber = mRetrofitHelper.refreshDisRadio(noteClassId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<DiscoveryEntity.RadioBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<DiscoveryEntity.RadioBean>) {
                        mView?.showRefreshRadio(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshPaid(noteClassId: String) {
        val subscriber = mRetrofitHelper.refreshDisPaid(noteClassId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<DiscoveryEntity.PaidSelectionBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<DiscoveryEntity.PaidSelectionBean>) {
                        mView?.shoeRefreshPaidData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshBroadcat(noteClassId: String) {
        val subscriber = mRetrofitHelper.refreshFree(noteClassId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<DiscoveryEntity.BroadcastBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<DiscoveryEntity.BroadcastBean>) {
                        mView?.showrefreshBroadcatData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getDiscovery() {
        val subscriber = mRetrofitHelper.getDisCovery()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<DiscoveryEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<DiscoveryEntity>) {
                        mView?.showDiscovery(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }
}