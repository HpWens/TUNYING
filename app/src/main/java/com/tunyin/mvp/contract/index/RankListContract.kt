package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.RankListEntity

class RankListContract {
    interface View : BaseContract.BaseView {
        fun showRankListData(rankListEntity: RankListEntity)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getRankList(offset: String, limit: String,type:String)
    }

}