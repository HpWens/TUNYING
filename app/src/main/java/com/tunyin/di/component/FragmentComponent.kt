package com.tunyin

import android.app.Activity
import com.tunyin.ui.fragment.discovery.DiscoveryFragment
import com.tunyin.ui.fragment.index.*
import com.tunyin.ui.fragment.mine.MineFragment
import com.tunyin.ui.fragment.mine.MyVoucherFragment
import com.tunyin.ui.fragment.purchased.PurchasedFragment


import dagger.Component

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/9/12
 * desc: FragmentComponent
 */
@FragmentScope
@Component(dependencies = [ApiComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    val activity: Activity

    fun inject(discoveryFragment: DiscoveryFragment)
    //
    fun inject(selectionFragment: SectionFragment)

    fun inject(indexFragment: IndexFragment)
    fun inject(playerDirectoryFragment: PlayerDirectoryFragment)
    fun inject(playerCommentFragment: PlayerCommentFragment)
    fun inject(rankingListFragment: RankingListFragment)
    fun inject(purchasedFragment: PurchasedFragment)
    fun inject(myVoucherFragment: MyVoucherFragment )
    fun inject(mineFragment: MineFragment)
    fun inject(playerDetailFragment: PlayerDetailFragment)


//
//    fun inject(selectionTypeFragment: SectionTypeFragment)
//
//    fun inject(mineFragment: MineFragment)
//
//    fun inject(courseCategoryFragment: CourseCategoryFragment)
}
