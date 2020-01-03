package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.ToastUtils
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.OrderEntity

class OrderContract {
    interface View : BaseContract.BaseView {
        fun showOrderList(orderEntity: OrderEntity)
        fun delOrderSuc(string: String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getOrderList(offset: String, limit: String)
        fun delOrder(orderId: String)
    }

}