package com.tunyin.mvp.contract.mine

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.model.mine.CollectEntity
import com.tunyin.mvp.model.mine.TunyinVipEntity

class TunyinVipContract {
    interface View : BaseContract.BaseView {
        fun getTunyinVipCallBack(tunyinVipEntity: TunyinVipEntity )
        fun payTunyinCallback(string: String)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getTunyinVip()
        fun payTunyinVip(nobleEquityId: String,type: String)
    }

}