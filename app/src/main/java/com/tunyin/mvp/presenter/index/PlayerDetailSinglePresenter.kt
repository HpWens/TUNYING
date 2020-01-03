package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.ToastUtils
import com.tunyin.base.BaseSubscriberPro
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.index.PlayDetailSingleContract
import com.tunyin.mvp.contract.index.PlayerCommentContract
import com.tunyin.mvp.contract.index.PlayerDirectoryContract
import com.tunyin.mvp.model.index.AddCommendEntity
import com.tunyin.mvp.model.index.PalyDetailSingleEntity
import com.tunyin.mvp.model.index.PlayerCommentEntity
import com.tunyin.mvp.model.index.PlayerDirectoryEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class PlayerDetailSinglePresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<PlayDetailSingleContract.View>(), PlayDetailSingleContract.Presenter<PlayDetailSingleContract.View> {

    override fun getMusicDetialSingle(songId: String) {
        val subscriber = mRetrofitHelper.getMusicDetailSingle(songId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<PalyDetailSingleEntity>(mView) {
                    override fun onSuccess(m: PalyDetailSingleEntity?) {
                        mView?.getMusicDetialSingleData(m!!)
                    }
                })
        addSubscribe(subscriber)
    }
}