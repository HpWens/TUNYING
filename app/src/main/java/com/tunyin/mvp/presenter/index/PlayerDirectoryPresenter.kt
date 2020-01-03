package com.tunyin.mvp.presenter.index

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriberPro
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.index.PlayerDirectoryContract
import com.tunyin.mvp.model.index.PlayerDirectoryEntity
import com.tunyin.utils.rxSchedulerHelper
import javax.inject.Inject

class PlayerDirectoryPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<PlayerDirectoryContract.View>(), PlayerDirectoryContract.Presenter<PlayerDirectoryContract.View> {
    override fun getDirectory(songId:String) {
        val subscriber = mRetrofitHelper.getPlayerDirectory(songId)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriberPro<PlayerDirectoryEntity>(mView) {
                    override fun onSuccess(mData: PlayerDirectoryEntity) {
//                        if (mData.code=="400"){
//                            ToastUtils.showToast(mData.desc)
//                        }else{
                        mView?.showDirectory(mData)
//                        }

                    }

                })
        addSubscribe(subscriber)
    }
}