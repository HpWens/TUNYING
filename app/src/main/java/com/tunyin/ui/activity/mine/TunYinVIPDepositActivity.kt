package com.tunyin.ui.activity.mine

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.meis.base.mei.utils.ParseJsonUtils
import com.tunyin.R
import com.tunyin.base.BaseInjectActivity
import com.tunyin.base.WebActivity
import com.tunyin.mvp.contract.mine.TunyinVipContract
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.mine.TunyinVipEntity
import com.tunyin.mvp.presenter.mine.TunyinVipPresenter
import com.tunyin.ui.adapter.mine.TunYinVipDepositAdapter
import com.tunyin.utils.AppUtils
import com.tunyin.utils.HttpUtils
import com.tunyin.utils.StatusBarUtil
import com.vondear.rxtool.view.RxToast
import com.zhouyou.http.callback.SimpleCallBack
import com.zhouyou.http.exception.ApiException
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
        ly_pay.setOnClickListener(this)

        // ImageUtil.load(SelfBean.instance.headUrl).isCircle.on(iv_avatar)
        Glide.with(this@TunYinVIPDepositActivity)
                .load(SelfBean.instance.headUrl)
                .transform(CircleCrop())
                .into(iv_avatar)

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

        tv_web.setOnClickListener{
            startActivity(mContext?.let { WebActivity.newIntent(it, "豚音贵族服务协议", "http://api.itunyin.com/api/html/h5?type=nobleservice") })
        }

        tv_Web2.setOnClickListener {
            startActivity(mContext?.let { WebActivity.newIntent(it, "连续包月服务协议", "http://api.itunyin.com/api/html/h5?type=continuous") })
        }
    }

    override fun payTunyinCallback(string: String) {
    }

    override fun onClick(p0: View?) {
        when (p0) {
            ly_pay -> {
                if (TextUtils.isEmpty(nobleEquityId)) {
                    RxToast.showToast("请选择贵族等级")
                    return
                }
                var type = ""
                when {
                    rb_monthly.isChecked -> {
                        type = "isAuto"
                    }
                    rb_1.isChecked -> {
                        type = "oneMonth"
                    }
                    rb_6.isChecked -> {
                        type = "sixMonth"
                    }
                    rb_12.isChecked -> {
                        type = "oneYear"
                    }
                }
                if (TextUtils.isEmpty(type)) {
                    RxToast.showToast("请选择充值时间")
                    return
                }
                ly_pay.isEnabled = false
                HttpUtils.getInstance().confirmPayment(nobleEquityId, type, object : SimpleCallBack<String>() {
                    override fun onSuccess(t: String?) {
                        var result = ParseJsonUtils.parseDataToResult(t, String::class.java)

                        var intent = Intent(this@TunYinVIPDepositActivity, RechargeResultActivity::class.java)
                        intent.putExtra("is_success", result.isOk)
                        startActivity(intent)

                        if (result.isOk) {
                            finish()
                        } else {
                            ly_pay.isEnabled = true
                        }
                    }

                    override fun onError(e: ApiException?) {
                        ly_pay.isEnabled = true
                    }
                })
            }
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