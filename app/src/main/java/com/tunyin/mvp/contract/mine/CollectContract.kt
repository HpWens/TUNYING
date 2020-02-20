package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.mine.CollectEntity

class CollectContract {
    interface View : BaseContract.BaseView {
        fun showCollectData(collectEntity: CollectEntity)
        override fun showError(msg: String)
        fun showCancelCollectSuccess(pos: Int)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getCollect(offset: String, limit: String)

        fun cancelCollect(pos: Int, songId: String)
    }

}