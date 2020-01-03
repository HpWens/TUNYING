package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class SearchHotPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<SearchContract.View>(), SearchContract.Presenter<SearchContract.View> {

    override fun deleteSearch(id: String) {
        if (id == "0") {//删全部
            val subscriber = mRetrofitHelper.deleteSearch(id)
                    .compose(rxSchedulerHelper())
                    .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                        override fun onSuccess(mData: BaseEntity<String>) {
                            mView?.deleteSearchAllSuc(mData.content)
                        }
                    })
            addSubscribe(subscriber)
        } else {
            val subscriber = mRetrofitHelper.deleteSearch(id)
                    .compose(rxSchedulerHelper())
                    .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                        override fun onSuccess(mData: BaseEntity<String>) {
                            mView?.deleteSearchSuc(mData.content)
                        }
                    })
            addSubscribe(subscriber)
        }

    }

    override fun getSearchHistory() {
        val subscriber = mRetrofitHelper.searchHistory()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<SearchHistoryEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<SearchHistoryEntity>) {
                        mView?.showSearchHistoryData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getSearchHot() {
        val subscriber = mRetrofitHelper.searchHot()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<SearchHotEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<SearchHotEntity>) {
                        mView?.showSearchHotData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }


}