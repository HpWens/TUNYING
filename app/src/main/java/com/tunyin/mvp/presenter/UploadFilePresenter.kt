package com.tunyin.mvp.presenter

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.UploadFileContract
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.utils.rxSchedulerHelper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class UploadFilePresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<UploadFileContract.View>(), UploadFileContract.Presenter<UploadFileContract.View> {
    override fun uploadFile(part: MultipartBody.Part) {
        val subscriber = mRetrofitHelper.fileUpload(part)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<UploadFileEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<UploadFileEntity>) {
                        mView?.uploadFileSuc(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }

}