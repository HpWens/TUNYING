package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.contract.index.SearchResultContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class SearchResultPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<SearchResultContract.View>(), SearchResultContract.Presenter<SearchResultContract.View> {
    override fun search(offset: String, limit: String, name: String, classifyId: String) {
        val subscriber = mRetrofitHelper.search(offset, limit, name, classifyId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<SearchEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<SearchEntity>) {
                        mView?.showSearchData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }




}