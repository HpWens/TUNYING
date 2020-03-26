package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.UploadFileEntity
import okhttp3.MultipartBody

class FeedbackContract {

    interface View : BaseContract.BaseView {
        override fun showError(msg: String)
        fun uploadPicSus(uploadFileEntity: UploadFileEntity)
        fun feedbackSus(respose: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun uploadFile(part: MultipartBody.Part)
        fun submitFeedback(contnet: String, picUrl: String)
    }
}
