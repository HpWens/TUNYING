package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.mvp.model.index.PlayerDirectoryEntity

class PlayerDirectoryContract {
    interface View : BaseContract.BaseView {
        fun showDirectory(playerDirectoryEntity: PlayerDirectoryEntity)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getDirectory(songId: String)
    }
}