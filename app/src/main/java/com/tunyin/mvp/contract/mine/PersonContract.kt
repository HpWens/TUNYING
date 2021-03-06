package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.UploadFileEntity
import okhttp3.MultipartBody


class PersonContract {
    interface View : BaseContract.BaseView {
        override fun showError(msg: String)
        fun uploadFileSuc(uploadFileEntity: UploadFileEntity)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun uploadFile(part: MultipartBody.Part)
    }
}