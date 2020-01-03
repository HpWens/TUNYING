package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity

class SearchContract {
    interface View : BaseContract.BaseView {
        fun showSearchHotData(searchHotEntity: SearchHotEntity)
        fun showSearchHistoryData(searchHistoryEntity: SearchHistoryEntity)
//        fun showSearchData(freeListBean: IndexEntity.FreeListBean)
        fun deleteSearchSuc(suc:String)
        fun deleteSearchAllSuc(suc:String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getSearchHot()
        fun getSearchHistory()
        fun deleteSearch(id:String)
//        fun getSearch(noteClassId:String)
    }

}