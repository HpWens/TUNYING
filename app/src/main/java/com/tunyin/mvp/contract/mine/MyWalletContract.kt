package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.mine.MyWalletEntity
import com.tunyin.mvp.model.mine.PayInfoEntity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MyWalletContract {
    interface View : BaseContract.BaseView {
        fun getMyWalletSuc(myWalletEntity: MyWalletEntity)
        fun getPayInfoSuc(payInfoEntity: PayInfoEntity)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getMyWallet()
        fun getPayInfo(map: Map<String, String>)

    }

}