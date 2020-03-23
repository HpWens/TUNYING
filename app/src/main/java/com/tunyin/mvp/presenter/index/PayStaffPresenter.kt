package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.PayStuffContract
import com.tunyin.mvp.contract.index.RankListContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.PayStaffBannerEntity
import com.tunyin.mvp.model.index.PayStuffEntity
import com.tunyin.mvp.model.index.RankListEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class PayStaffPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<PayStuffContract.View>(), PayStuffContract.Presenter<PayStuffContract.View> {

    override fun getBanner() {
        val subscriber = mRetrofitHelper.paidSelectionBanner()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<PayStaffBannerEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<PayStaffBannerEntity>) {
                        mView?.showBannerData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getPayStaff(offset: String, limit: String, typeId: String) {
        val subscriber = mRetrofitHelper.paidSelection(offset, limit, typeId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<PayStuffEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<PayStuffEntity>) {
                        mView?.showPayStaff(mData.content)
                    }
                })
        addSubscribe(subscriber)

    }


}