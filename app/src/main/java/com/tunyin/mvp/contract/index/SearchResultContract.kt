package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity

class SearchResultContract {
    interface View : BaseContract.BaseView {
        fun showSearchData(searchEntity: SearchEntity)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun search(offset: String, limit: String, name: String, classifyId: String)
    }

}