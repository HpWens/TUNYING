package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.RankListContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.RankListEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class RankListPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<RankListContract.View>(), RankListContract.Presenter<RankListContract.View> {
    override fun getRankList(offset: String, limit: String, type: String) {
        val subscriber = mRetrofitHelper.rankList(offset, limit, type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<RankListEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<RankListEntity>) {
                        mView?.showRankListData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

}