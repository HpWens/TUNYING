package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.UploadFileContract
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.contract.mine.MyWalletContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.mine.MyWalletEntity
import com.tunyin.mvp.model.mine.PayInfoEntity
import com.tunyin.utils.rxSchedulerHelper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class MyWalletPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<MyWalletContract.View>(), MyWalletContract.Presenter<MyWalletContract.View> {
    override fun getPayInfo(map: Map<String, String>) {
        val subscriber = mRetrofitHelper.getPayInfo(map)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<PayInfoEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<PayInfoEntity>) {
                        mView?.getPayInfoSuc(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun getMyWallet() {
        val subscriber = mRetrofitHelper.myWallet()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<MyWalletEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<MyWalletEntity>) {
                        mView?.getMyWalletSuc(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }


}