package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.contract.index.SearchResultContract
import com.tunyin.mvp.contract.mine.CollectContract
import com.tunyin.mvp.contract.mine.VoucherContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.VoucherEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class VoucherPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<VoucherContract.View>(), VoucherContract.Presenter<VoucherContract.View> {
    override fun getVoucher(offset: String, limit: String, type: String) {
        val subscriber = mRetrofitHelper.myVoucher(offset, limit, type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<VoucherEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<VoucherEntity>) {
                        mView?.showVoucherData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }


}