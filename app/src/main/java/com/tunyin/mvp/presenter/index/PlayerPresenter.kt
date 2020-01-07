package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriberPro
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.index.PlayerContract
import com.tunyin.mvp.model.index.CreateOrderEntity
import com.tunyin.mvp.model.index.MusicEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class PlayerPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<PlayerContract.View>(), PlayerContract.Presenter<PlayerContract.View> {

    override fun payOrder(orderId: String) {
        val subscriber = mRetrofitHelper.payOrder(orderId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<String>(mView) {
                    override fun onSuccess(mData: String) {
                        mView?.payOrderCallBack(mData)
                    }

                })
        addSubscribe(subscriber)
    }



    override fun createOrder(songDetails: String, couponId: String,themeId:String) {
        val subscriber = mRetrofitHelper.createOrder(songDetails, couponId,themeId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<CreateOrderEntity>(mView) {
                    override fun onSuccess(mData: CreateOrderEntity) {
//                        if (mData.code=="400"){
//                            ToastUtils.showToast(mData.desc)
//                        }else{
                        mView?.cerateOrderCallBack(mData)
//                        }

                    }

                })
        addSubscribe(subscriber)
    }

    override fun getMusic(songId: String) {
        val subscriber = mRetrofitHelper.getMusic(songId)
                .compose(rxSchedulerHelper())
                .retry(3)
                .subscribeWith(object : BaseSubscriberPro<MusicEntity>(mView) {
                    override fun onSuccess(mData: MusicEntity) {
//                        if (mData.code=="400"){
//                            ToastUtils.showToast(mData.desc)
//                        }else{
                        mView?.showMusicData(mData)
//                        }

                    }

                })
        addSubscribe(subscriber)
    }
}