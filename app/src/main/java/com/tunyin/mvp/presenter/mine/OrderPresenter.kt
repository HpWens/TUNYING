package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.contract.index.SearchResultContract
import com.tunyin.mvp.contract.mine.CollectContract
import com.tunyin.mvp.contract.mine.OrderContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.OrderEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class OrderPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<OrderContract.View>(), OrderContract.Presenter<OrderContract.View> {
    override fun delOrder(orderId: String) {
        val subscriber = mRetrofitHelper.getOrderList(orderId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.delOrderSuc(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getOrderList(offset: String, limit: String) {
        val subscriber = mRetrofitHelper.getOrderList(offset, limit)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<OrderEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<OrderEntity>) {
                        mView?.showOrderList(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }


}