package com.tunyin.ui.activity.index

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.index.PayStuffContract
import com.tunyin.mvp.model.index.PayStaffBannerEntity
import com.tunyin.mvp.model.index.PayStuffEntity
import com.tunyin.mvp.presenter.index.PayStaffPresenter
import com.tunyin.ui.adapter.discovery.DiscoveryRVAdapter
import com.tunyin.ui.adapter.index.PayStaffBannerSelection
import com.tunyin.ui.adapter.index.PayStuffForAcSelection
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_pay_stuff.*
import kotlinx.android.synthetic.main.layout_toolbar.toolbar
import kotlinx.android.synthetic.main.layout_toolbar.tv_title

/**
 * create by wangrongchao
 * on 2019/11/2
 *付费精品
 **/
class PayStuffActivity : BaseInjectActivity<PayStaffPresenter>(), PayStuffContract.View {

    // 2 广播剧 4 电台
    private var typeId = "0"
    private var name = ""

    private val mBannerList = ArrayList<PayStaffBannerEntity.ListBean>() // 顶部
    private val mPayStuffList = ArrayList<PayStuffEntity>() //

    private var mDiscoveryRVAdapter: DiscoveryRVAdapter? = null

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)

    override fun showBannerData(payStaffBannerEntity: PayStaffBannerEntity) {
        mBannerList.addAll(payStaffBannerEntity.list)
        mPresenter.getPayStaff("0", "200", typeId)
    }

    override fun showPayStaff(payStuffEntity: PayStuffEntity) {
        mPayStuffList.clear()
        mPayStuffList.add(payStuffEntity)
        finishTask()
        hideLoading()
    }

    fun finishTask() {
        if (mBannerList.size != 0) mDiscoveryRVAdapter?.addSection(PayStaffBannerSelection(mBannerList))
        if (mPayStuffList.size != 0) mDiscoveryRVAdapter?.addSection(PayStuffForAcSelection(mPayStuffList, typeId))
        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_pay_stuff
    }

    override fun initWidget() {
//        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.transparent))
        intent?.let {
            typeId = intent.getStringExtra(TYPE_ID)
            name = intent.getStringExtra(NAME);
        }

        StatusBarUtil.setTranslucentForImageView(this, 0, null)

        tv_title.text = name

//        when (typeId) {
//            BROAD_CAST -> tv_title.text = "广播剧"
//            STATION -> tv_title.text = "电台"
//            "5" -> tv_title.text = "主播哄睡"
//            "6" -> tv_title.text = "自然声"
//            "7" -> tv_title.text = "无人声"
//            else -> tv_title.text = "付费精选"
//        }

        toolbar.setBackgroundResource(R.color.transpaent_bg)

        mDiscoveryRVAdapter = DiscoveryRVAdapter()
        val mLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.layoutManager = mLayoutManager
        recycler?.adapter = mDiscoveryRVAdapter
        showLoading()
        mPresenter.getBanner(typeId)
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, PayStuffActivity::class.java)
            return intent
        }

        val TYPE_ID = "type_id"

        val NAME = "name"

        @kotlin.jvm.JvmField
        val BROAD_CAST = "2"

        @kotlin.jvm.JvmField
        val STATION = "4"

        @JvmStatic
        fun newInstance(context: Context?, typeId: String = "0", name: String): Intent {
            val intent = Intent(context, PayStuffActivity::class.java)
            intent.putExtra(TYPE_ID, typeId)
            intent.putExtra(NAME, name)
            return intent
        }

        @JvmStatic
        fun newAnchorInstance(context: Context?): Intent {
            val intent = Intent(context, PayStuffActivity::class.java)
            intent.putExtra(TYPE_ID, "-1")
            intent.putExtra(NAME, "主播哄睡")
            return intent
        }

    }
}