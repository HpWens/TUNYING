package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.contract.index.SearchResultContract
import com.tunyin.mvp.contract.mine.CollectContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.CancelCollectEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.CollectEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class CollectPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<CollectContract.View>(), CollectContract.Presenter<CollectContract.View> {


    override fun addCollect(pos: Int, songId: String) {
        val subscriber = mRetrofitHelper.addCollect(songId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.showAddCollectSuccess(pos)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun cancelCollect(pos: Int, songId: String) {
        val subscriber = mRetrofitHelper.cancelCollect(songId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<CancelCollectEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<CancelCollectEntity>) {
                        mView?.showCancelCollectSuccess(pos)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getCollect(offset: String, limit: String) {
        val subscriber = mRetrofitHelper.myCollect(offset, limit)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<CollectEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<CollectEntity>) {
                        mView?.showCollectData(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }


}