package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.mvp.model.index.AddCommendEntity
import com.tunyin.mvp.model.index.PlayerCommentEntity
import com.tunyin.mvp.model.index.PlayerDirectoryEntity

class PlayerCommentContract {
    interface View : BaseContract.BaseView {
        fun showComment(playerCommentEntity:PlayerCommentEntity)
        fun addCommendData(addCommendEntity: AddCommendEntity)
        override fun showError(msg: String)
        fun addPraiseSuc(str:String)
        fun cancelPraiseSuc(str:String)
    }
    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getComment(songId:String)
        fun addPraise(commentId:String)
        fun cancelPraise(commentId:String)
        fun addCommend(songId:String,content:String)
    }
}