package com.tunyin.ui.activity.mine

import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseActivity
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.TunyinVipContract
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.mine.TunyinVipEntity
import com.tunyin.mvp.presenter.mine.TunyinVipPresenter
import com.tunyin.ui.adapter.mine.TunYinVipDepositAdapter
import com.tunyin.utils.AppUtils
import com.tunyin.utils.ImageUtil
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_vip_deposit.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 豚音贵族充值
 */
class TunYinVIPDepositActivity : BaseInjectActivity<TunyinVipPresenter>(), View.OnClickListener, TunyinVipContract.View {

    private var nobleEquityId: String? = null
    private var type: String? = null

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun getTunyinVipCallBack(tunyinVipEntity: TunyinVipEntity) {
        vipTypeList.addAll(tunyinVipEntity.list)
        tunYinVipDepositAdapter.dataList = vipTypeList
        tunYinVipDepositAdapter.notifyDataSetChanged()
    }

    private lateinit var tunYinVipDepositAdapter: TunYinVipDepositAdapter
    private val vipTypeList = ArrayList<TunyinVipEntity.ListBean>()
    private val listRb = arrayListOf<RadioButton>()
    override fun getLayoutId(): Int {
        return R.layout.activity_vip_deposit
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "豚音贵族充值"
        rb_monthly.setOnClickListener(this)
        rb_1.setOnClickListener(this)
        rb_6.setOnClickListener(this)
        rb_12.setOnClickListener(this)
        rb_diamond.setOnClickListener(this)
        rl_monthly.setOnClickListener(this)
        rl_1.setOnClickListener(this)
        rl_6.setOnClickListener(this)
        rl_12.setOnClickListener(this)
        rl_diamond.setOnClickListener(this)
        ImageUtil.load(SelfBean.instance.headUrl).isCircle.on(iv_avatar)
        tv_name.text = SelfBean.instance.nickName

        tunYinVipDepositAdapter = TunYinVipDepositAdapter()
        tunYinVipDepositAdapter.setOnItemClickListener { _, positon ->
            tunYinVipDepositAdapter.setDefSelect(positon)
            nobleEquityId = tunYinVipDepositAdapter.dataList[positon].id
        }
        recycler.layoutManager = GridLayoutManager(mContext, 2)
        recycler.adapter = tunYinVipDepositAdapter

//        vipTypeList.add("贵族·骑士")
//        vipTypeList.add("贵族·男爵")
//        vipTypeList.add("贵族·子爵")
//        vipTypeList.add("贵族·伯爵")
//        vipTypeList.add("贵族·侯爵")
//        vipTypeList.add("贵族·公爵")

        mPresenter.getTunyinVip()

    }

    override fun payTunyinCallback(string: String) {
    }

    override fun onClick(p0: View?) {
        when (p0) {
            rb_monthly, rl_monthly -> {
                if (listRb.contains(rb_monthly)) {
                    return
                } else {
                    if (listRb.size != 0) {
                        for (index in 0 until listRb.size) {
                            listRb[index].isChecked = false
                        }
                    }
                    listRb.clear()
                    listRb.add(rb_monthly)
                    rb_monthly.isChecked = true
                }

            }
            rb_1, rl_1 -> {
                if (listRb.contains(rb_1)) {
                    return
                } else {
                    if (listRb.size != 0) {
                        for (index in 0 until listRb.size) {
                            listRb[index].isChecked = false
                        }
                    }
                    listRb.clear()
                    listRb.add(rb_1)
                    rb_1.isChecked = true
                }

            }
            rb_6, rl_6 -> {
                if (listRb.contains(rb_6)) {
                    return
                } else {
                    if (listRb.size != 0) {
                        for (index in 0 until listRb.size) {
                            listRb[index].isChecked = false
                        }
                    }
                    listRb.clear()
                    listRb.add(rb_6)
                    rb_6.isChecked = true
                }

            }
            rb_12, rl_12 -> {
                if (listRb.contains(rb_12)) {
                    return
                } else {
                    if (listRb.size != 0) {
                        for (index in 0 until listRb.size) {
                            listRb[index].isChecked = false
                        }
                    }
                    listRb.clear()
                    listRb.add(rb_12)
                    rb_12.isChecked = true
                }

            }
            rb_diamond, rl_diamond -> {
                rb_diamond.isChecked = true

            }
        }
    }
}