package com.tunyin.mvp.presenter.mine

import com.tunyin.RetrofitHelper
import com.tunyin.base.BaseSubscriber
import com.tunyin.base.RxPresenter
import com.tunyin.mvp.contract.mine.FeedbackContract
import com.tunyin.mvp.contract.mine.ResetPasswordContract
import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.utils.rxSchedulerHelper
import okhttp3.MultipartBody
import javax.inject.Inject

class FeedbackPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) :
        RxPresenter<FeedbackContract.View>(), FeedbackContract.Presenter<FeedbackContract.View> {

    override fun submitFeedback(contnet: String, picUrl: String) {
        val subscriber = mRetrofitHelper.postFeedback(contnet,picUrl)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<String>>(mView) {
                    override fun onSuccess(mData: BaseEntity<String>) {
                        mView?.feedbackSus(mData.content)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
        addSubscribe(subscriber)
    }

    override fun uploadFile(part: MultipartBody.Part) {
        val subscriber = mRetrofitHelper.fileUpload(part)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<BaseEntity<UploadFileEntity>>(mView) {
                    override fun onSuccess(mData: BaseEntity<UploadFileEntity>) {
                        mView?.uploadPicSus(mData.content)
                    }
                })
        addSubscribe(subscriber)
    }
}
