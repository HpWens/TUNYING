package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.ToastUtils
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.*

class PayStuffContract {
    interface View : BaseContract.BaseView {
        fun showBannerData(payStaffBannerEntity: PayStaffBannerEntity)
        fun showPayStaff(payStuffEntity: PayStuffEntity)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getBanner(noteClassId: String)
        fun getPayStaff(offset: String, limit: String, typeId: String)
    }

}