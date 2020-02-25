package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.ToastUtils
import com.tunyin.mvp.model.index.CreateOrderEntity
import com.tunyin.mvp.model.index.MusicEntity

class PlayerContract {
    interface View : BaseContract.BaseView {
        fun showMusicData(musicEntity: MusicEntity)
        fun cerateOrderCallBack(createOrderEntity: CreateOrderEntity)
        fun payOrderCallBack(string: String)
        fun showCancelCollectSuccess()
        fun showAddCollectSuccess()
        override fun showError(msg: String) {
            ToastUtils.showToast(msg)
        }
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getMusic(songId: String)
        fun createOrder(songDetails: String, couponId: String, themeId: String)
        fun payOrder(orderId: String)
        fun cancelCollect(songId: String)
        fun addCollect(songId: String)
    }


}