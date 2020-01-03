package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.VoucherEntity

class VoucherContract {
    interface View : BaseContract.BaseView {
        fun showVoucherData(voucherEntity: VoucherEntity)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getVoucher(offset: String, limit: String, type: String)
    }

}