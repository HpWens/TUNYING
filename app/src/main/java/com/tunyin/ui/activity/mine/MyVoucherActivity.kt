package com.tunyin.ui.activity.mine

import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.tunyin.BaseFragment
import com.tunyin.R
import com.tunyin.base.BaseActivity
import com.tunyin.mvp.model.FlyoutTabEntity
import com.tunyin.ui.adapter.PagerAdapter
import com.tunyin.ui.fragment.mine.MyVoucherFragment
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_my_voucher.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.ArrayList

/**
 * 我的优惠券
 */
class MyVoucherActivity : BaseActivity(), OnTabSelectListener, ViewPager.OnPageChangeListener {

    private var adapter: PagerAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_my_voucher
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "我的优惠券"

        val mTabEntities = ArrayList<CustomTabEntity>()
        val fragments = ArrayList<BaseFragment>()

        mTabEntities.add(FlyoutTabEntity("可使用"))
        mTabEntities.add(FlyoutTabEntity("已使用"))
        mTabEntities.add(FlyoutTabEntity("已失效"))

        fragments.add(MyVoucherFragment.newInstance("1"))
        fragments.add(MyVoucherFragment.newInstance("2"))
        fragments.add(MyVoucherFragment.newInstance("3"))

        mTabLayout.setTabData(mTabEntities)
        mTabLayout.setOnTabSelectListener(this)

        adapter = PagerAdapter(supportFragmentManager, fragments)
        mViewPager.adapter = adapter
        mViewPager.addOnPageChangeListener(this)
    }


    override fun onTabSelect(position: Int) {
        mViewPager.currentItem = position
    }

    override fun onTabReselect(position: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mTabLayout.currentTab = position
    }
}