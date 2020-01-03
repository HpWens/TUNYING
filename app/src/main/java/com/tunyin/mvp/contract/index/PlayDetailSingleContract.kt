package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.mvp.model.index.AddCommendEntity
import com.tunyin.mvp.model.index.PalyDetailSingleEntity
import com.tunyin.mvp.model.index.PlayerCommentEntity
import com.tunyin.mvp.model.index.PlayerDirectoryEntity

class PlayDetailSingleContract {
    interface View : BaseContract.BaseView {
        fun getMusicDetialSingleData(palyDetailSingleEntity: PalyDetailSingleEntity)

    }
    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getMusicDetialSingle(songId:String)
    }
}