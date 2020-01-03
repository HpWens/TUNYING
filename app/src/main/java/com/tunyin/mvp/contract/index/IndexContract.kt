package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.tunyin.ToastUtils
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.adapter.index.SleepTopSelection

class IndexContract {
    interface View : BaseContract.BaseView {
        fun showIndex(indexEntity: IndexEntity)
        //        fun showRefreshFreeData(freeListBean: IndexEntity.FreeListBean)
        fun showRefreshGuessLike(guessLikeBean: IndexEntity.GuessLikeBean)

        fun showRefreshSleepTop(sleepTopBean: IndexEntity.SleepTopBean)
        fun showRefreshHotAnchor(anchorListBean: IndexEntity.AnchorListBean)
        fun showRefreshFree(freeListBean: IndexEntity.FreeListBean)
        fun showRreshFeaturedRecommend(featuredRecommendBean: IndexEntity.FeaturedRecommendBean)
        override fun showError(msg: String){
            ToastUtils.showToast(msg)
        }

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getIndex()
        //        fun refreshFree(noteClassId: String)
        fun refreshGuessLike()

        fun refreshSleepTop()
        fun refreshHotAnchor()
        fun refreshFree()
        fun refreshFeaturedRecommend()
    }

}