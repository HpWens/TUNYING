package com.tunyin.mvp.presenter

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.UploadFileContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.utils.rxSchedulerHelper
import okhttp3.MultipartBody
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


    fun updateHeader(header: String) {
        val subscriber = mRetrofitHelper.refreshHeader(header)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                    }
                })
        addSubscribe(subscriber)
    }

}