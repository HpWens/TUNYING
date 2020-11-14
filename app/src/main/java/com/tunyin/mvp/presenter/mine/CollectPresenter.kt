package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.CollectContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.CancelCollectEntity
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

                    override fun onFailure(code: Int, message: String) {
                        super.onFailure(code, message)
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