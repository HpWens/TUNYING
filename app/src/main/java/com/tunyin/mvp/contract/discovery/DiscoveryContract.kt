package com.tunyin.mvp.contract.discovery

import com.tunyin.BaseContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity

class DiscoveryContract {
    interface View : BaseContract.BaseView {
        fun showDiscovery(discoveryEntity: DiscoveryEntity)
        fun shoeRefreshPaidData(paidSelectionBean: DiscoveryEntity.PaidSelectionBean)
        fun showrefreshBroadcatData(broadcastBean: DiscoveryEntity.BroadcastBean)
        fun showRefreshThemeSleep(themeSleepBean: DiscoveryEntity.ThemeSleepBean)
        fun showRefreshRadio(radioBean: DiscoveryEntity.RadioBean)
        override fun showError(msg: String)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getDiscovery()
        fun refreshPaid(noteClassId: String)
        fun refreshBroadcat(noteClassId: String)
        fun refreshThemeSleep(noteClassId: String)
        fun refreshRadio(noteClassId: String)
    }

}