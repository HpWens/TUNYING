package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.CollectEntity

class CollectContract {
    interface View : BaseContract.BaseView {
        fun showCollectData(collectEntity: CollectEntity )
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getCollect(offset: String, limit: String)
    }

}