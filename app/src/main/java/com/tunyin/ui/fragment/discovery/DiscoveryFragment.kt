package com.tunyin.ui.fragment.discovery

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.discovery.DiscoveryContract
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.presenter.discovery.DiscoveryPresenter
import com.tunyin.ui.activity.index.SearchActivity
import com.tunyin.ui.adapter.discovery.*
import kotlinx.android.synthetic.main.fragment_discovery.*

/**
 * 发现
 */
class DiscoveryFragment : BaseRefreshFragment<DiscoveryPresenter, DiscoveryEntity>(), View.OnClickListener, DiscoveryContract.View {


    private val mClassifyList = ArrayList<DiscoveryEntity>() // 顶部
    private val mPaidSelectionList = ArrayList<DiscoveryEntity.PaidSelectionBean.ListBeanXXXX>() // 付费精选
    private val mBroadcastList = ArrayList<DiscoveryEntity.BroadcastBean>() // 广播剧
    private val mThemeSleepList = ArrayList<DiscoveryEntity.ThemeSleepBean>() // 主题哄睡
    private val mRadioList = ArrayList<DiscoveryEntity.RadioBean.ListBeanXXXXX>() // 电台
    private val mNaturalSoundList = ArrayList<DiscoveryEntity.NaturalSoundBean>() // 自然声
    private val mUnmannedSoundList = ArrayList<DiscoveryEntity.UnmannedSoundBean>() // 无人声
    private var mDiscoveryRVAdapter: DiscoveryRVAdapter? = null


    override fun initRecyclerView() {
        mDiscoveryRVAdapter = DiscoveryRVAdapter()
        val mLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mDiscoveryRVAdapter
    }

    override fun getLayoutId(): Int = R.layout.fragment_discovery

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

//    override fun lazyLoadData() = mPresenter.getDiscovery()

    override fun lazyLoadData() {
        mPresenter.getDiscovery()
    }

    companion object {
        fun newInstance(): DiscoveryFragment {
            return DiscoveryFragment()
        }
    }

    override fun initWidget() {
        search_layout.setOnClickListener(this)
        toolbar.setOnClickListener(this)
        et_search_content.setOnClickListener(this)
        main_toolbar_item.setOnClickListener(this)
    }

    override fun clear() {
        mPaidSelectionList.clear()
        mBroadcastList.clear()
        mClassifyList.clear()
        mThemeSleepList.clear()
        mRadioList.clear()
        mNaturalSoundList.clear()
        mUnmannedSoundList.clear()
        mDiscoveryRVAdapter?.removeAllSections()

    }

    override fun onClick(p0: View?) {
        when (p0) {
            toolbar,
            main_toolbar_item,
            et_search_content,
            search_layout -> {
                val intent = Intent(activity, SearchActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }

    override fun showDiscovery(discoveryEntity: DiscoveryEntity) {
        et_search_content.hint = discoveryEntity.hotSearch
        mPaidSelectionList.addAll(discoveryEntity.paidSelection.list)
//        mBroadcastList.addAll(discoveryEntity.broadcast)
        mBroadcastList.add(discoveryEntity.broadcast)
        mClassifyList.add(discoveryEntity)
        mThemeSleepList.add(discoveryEntity.themeSleep)
        mRadioList.addAll(discoveryEntity.radio.list)
        mNaturalSoundList.add(discoveryEntity.naturalSound)
        mUnmannedSoundList.add(discoveryEntity.unmannedSound)
        finishTask()
    }

    override fun finishTask() {
        if (mClassifyList.size != 0) mDiscoveryRVAdapter?.addSection(ClassifySelection(mClassifyList))

        var discoveryPaidSelection = DiscoveryPaidSelection(mPaidSelectionList)
        discoveryPaidSelection.setRefreshPaidListener(object : DiscoveryPaidSelection.OnRefreshPaidListener {
            override fun refreshPaidData() {
                showLoading()
                mPresenter.refreshPaid("1")

            }

        })
        if (mPaidSelectionList.size != 0) mDiscoveryRVAdapter?.addSection(discoveryPaidSelection)


        var broadcatSelection = BroadcatSelection(mBroadcastList)
        broadcatSelection.setRefreshBroadcatListener(object : BroadcatSelection.OnRefreshBroadcatListener {
            override fun refreshBroadcatData() {
                showLoading()
                mPresenter.refreshBroadcat("2")
            }

        })
        if (mBroadcastList.size != 0) mDiscoveryRVAdapter?.addSection(broadcatSelection)

        var themeSleepSelection = ThemeSleepSelection(mThemeSleepList)
        themeSleepSelection.setRefreshThemeSleepListener(object : ThemeSleepSelection.OnRefreshThemeSleepListener {
            override fun refreshThemeSleepData() {
                showLoading()
                mPresenter.refreshThemeSleep("3")
            }

        })
        if (mThemeSleepList.size != 0) mDiscoveryRVAdapter?.addSection(themeSleepSelection)

        var radioSelection = RadioSelection(mRadioList)
        radioSelection.setRefreshRadioListener(object : RadioSelection.OnRefreshRadioListener {
            override fun refreshRadioData() {
                showLoading()
                mPresenter.refreshRadio("4")
            }

        })
        if (mRadioList.size != 0) mDiscoveryRVAdapter?.addSection(radioSelection)
        if (mNaturalSoundList.size != 0) mDiscoveryRVAdapter?.addSection(NatureSoundSelection(mNaturalSoundList))
        if (mUnmannedSoundList.size != 0) mDiscoveryRVAdapter?.addSection(UnmannedSoundSelection(mUnmannedSoundList))

        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }

    override fun shoeRefreshPaidData(paidSelectionBean: DiscoveryEntity.PaidSelectionBean) {
        mPaidSelectionList.clear()
        mPaidSelectionList.addAll(paidSelectionBean.list)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
        hideLoading()
    }

    override fun showrefreshBroadcatData(broadcastBean: DiscoveryEntity.BroadcastBean) {
        mBroadcastList.clear()
        mBroadcastList.add(broadcastBean)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
        hideLoading()

    }

    override fun showRefreshThemeSleep(themeSleepBean: DiscoveryEntity.ThemeSleepBean) {
        mThemeSleepList.clear()
        mThemeSleepList.add(themeSleepBean)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
        hideLoading()

    }

    override fun showRefreshRadio(radioBean: DiscoveryEntity.RadioBean) {
        mRadioList.clear()
        mRadioList.addAll(radioBean.list)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
        hideLoading()
    }

    override fun showError(msg: String) {
        hideLoading()
    }
}