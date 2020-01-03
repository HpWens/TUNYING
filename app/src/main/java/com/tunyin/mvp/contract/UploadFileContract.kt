package com.tunyin.mvp.contract

import com.tunyin.BaseContract
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadFileContract {
    interface View : BaseContract.BaseView {
        fun uploadFileSuc(uploadFileEntity: UploadFileEntity)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun uploadFile(part: MultipartBody.Part)
    }

}