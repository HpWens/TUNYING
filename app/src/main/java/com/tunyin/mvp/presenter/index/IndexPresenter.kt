package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class IndexPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<IndexContract.View>(), IndexContract.Presenter<IndexContract.View> {
    override fun refreshHotAnchor() {
        val subscriber = mRetrofitHelper.refreshHotAnchor()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity.AnchorListBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<IndexEntity.AnchorListBean>) {
                        mView?.showRefreshHotAnchor(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshFree() {
        val subscriber = mRetrofitHelper.refreshFree()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity.FreeListBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<IndexEntity.FreeListBean>) {
                        mView?.showRefreshFree(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshFeaturedRecommend() {
        val subscriber = mRetrofitHelper.refreshFeaturedRecommend()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity.FeaturedRecommendBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<IndexEntity.FeaturedRecommendBean>) {
                        mView?.showRreshFeaturedRecommend(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshSleepTop() {
        val subscriber = mRetrofitHelper.refreshSleepTop()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity.SleepTopBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<IndexEntity.SleepTopBean>) {
                        mView?.showRefreshSleepTop(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun refreshGuessLike() {
        val subscriber = mRetrofitHelper.refreshGuessLike()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity.GuessLikeBean>>(mView) {
                    override fun onSuccess(mData: BaseEntity<IndexEntity.GuessLikeBean>) {
                        mView?.showRefreshGuessLike(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

//    override fun refreshFree(noteClassId: String) {
//        val subscriber = mRetrofitHelper.refreshFree(noteClassId)
//                .compose(rxSchedulerHelper())
//                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity.FreeListBean>>(mView) {
//                    override fun onSuccess(mData: BaseEntity<IndexEntity.FreeListBean>) {
//                        mView?.showRefreshFreeData(mData.content)
//                    }
//                })
//        addSubscribe(subscriber)
//    }

    override fun getIndex() {
        val subscriber = mRetrofitHelper.getIndex()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<IndexEntity>) {
                        mView?.showIndex(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }
//
//    override fun refreshFreeData() {
//        val subscriber = mRetrofitHelper.getIndex()
//                .compose(rxSchedulerHelper())
//                .subscribeWith(object : BaseSubscriber<BaseEntity<IndexEntity>>(mView) {
//                    override fun onSuccess(mData: BaseEntity<IndexEntity>) {
//                        mView?.showIndex(mData.content)
//                    }
//                })
//        addSubscribe(subscriber)
//    }
}