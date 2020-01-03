package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.ToastUtils
import com.tunyin.base.BaseSubscriberPro
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.index.PlayerCommentContract
import com.tunyin.mvp.contract.index.PlayerDirectoryContract
import com.tunyin.mvp.model.index.AddCommendEntity
import com.tunyin.mvp.model.index.PlayerCommentEntity
import com.tunyin.mvp.model.index.PlayerDirectoryEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class PlayerCommentPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<PlayerCommentContract.View>(), PlayerCommentContract.Presenter<PlayerCommentContract.View> {
    override fun addCommend(songId: String, content: String) {
        val subscriber = mRetrofitHelper.addCommend(songId, content)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<AddCommendEntity>(mView) {
                    override fun onSuccess(m: AddCommendEntity) {
                        mView?.addCommendData(m)
                    }

                })
        addSubscribe(subscriber)
    }

    override fun cancelPraise(commentId: String) {
        val subscriber = mRetrofitHelper.cancelPraise(commentId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<String>(mView) {
                    override fun onSuccess(m: String) {
                        ToastUtils.showToast(m)
                        mView?.cancelPraiseSuc(m)
                    }

                })
        addSubscribe(subscriber)
    }

    override fun addPraise(commentId: String) {
        val subscriber = mRetrofitHelper.addPraise(commentId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<String>(mView) {
                    override fun onSuccess(m: String) {
                        ToastUtils.showToast(m)
                        mView?.addPraiseSuc(m)
                    }

                })
        addSubscribe(subscriber)
    }

    override fun getComment(songId: String) {
        val subscriber = mRetrofitHelper.getPlayerComment(songId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<PlayerCommentEntity>(mView) {
                    override fun onSuccess(mData: PlayerCommentEntity) {
//                        if (mData.code=="400"){
//                            ToastUtils.showToast(mData.desc)
//                        }else{
                        mView?.showComment(mData)
//                        }

                    }

                })
        addSubscribe(subscriber)
    }
}