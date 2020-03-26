package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.PersonContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.utils.rxSchedulerHelper
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonalPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<PersonContract.View>(), PersonContract.Presenter<PersonContract.View> {
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