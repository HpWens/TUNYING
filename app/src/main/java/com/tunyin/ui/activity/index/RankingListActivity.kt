package com.tunyin.ui.activity.index

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.tunyin.BaseFragment
import com.tunyin.R
import com.tunyin.base.BaseActivity
import com.tunyin.mvp.model.FlyoutTabEntity
import com.tunyin.ui.adapter.PagerAdapter
import com.tunyin.ui.fragment.index.RankingListFragment
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_ranking_list.*
import java.util.ArrayList

/**
 * create by wangrongchao
 * on 2019/11/7
 *排行榜
 **/
class RankingListActivity : BaseActivity(), OnTabSelectListener, ViewPager.OnPageChangeListener, View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0) {
            iv_back -> {
                finish()
            }
        }
    }


    private var adapter: PagerAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_ranking_list
    }

    override fun initVariables() {

        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        val mTabEntities = ArrayList<CustomTabEntity>()
        val fragments = ArrayList<BaseFragment>()

        mTabEntities.add(FlyoutTabEntity("付费版"))
        mTabEntities.add(FlyoutTabEntity("免费版"))
        fragments.add(RankingListFragment.newInstance("1"))
        fragments.add(RankingListFragment.newInstance("2"))

        mTabLayout.setTabData(mTabEntities)
        mTabLayout.setOnTabSelectListener(this)
        iv_back.setOnClickListener(this)

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


    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, RankingListActivity::class.java)
            return intent
        }
    }

}