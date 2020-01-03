package com.tunyin


import android.app.Activity
import com.tunyin.ui.activity.index.PayStuffActivity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.activity.index.SearchActivity
import com.tunyin.ui.activity.index.SearchResultActivity
import com.tunyin.ui.activity.mine.*
import dagger.Component

/**
 * @author: ym
 * date: 2018/9/10
 * desc:ActivityComponent
 */
@ActivityScope
@Component(dependencies = [ApiComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    val activity: Activity

    fun inject(activity: MainActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: PlayerActivity)

    //    fun inject(activity: ChoosePhaseActivity)
//
    fun inject(activity: MyWalletActivity)

    fun inject(searchActivity: SearchActivity)
    fun inject(searchResultActivity: SearchResultActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(myCollectActivity: MyCollectActivity)
    fun inject(forgetPwdActivity: ForgetPwdActivity)
    fun inject(payStuffActivity: PayStuffActivity)
    fun inject(myOrderActivity: MyOrderActivity)
    fun inject(orderDetailActivity: OrderDetailActivity)
    fun inject(tunYinVIPDepositActivity: TunYinVIPDepositActivity)


}
