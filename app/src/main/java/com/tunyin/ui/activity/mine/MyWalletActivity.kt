package com.tunyin.ui.activity.mine

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.alipay.sdk.app.PayTask
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.alipay.PayResult
import com.tunyin.base.BaseInjectActivity
import com.tunyin.event.WXPayEvent
import com.tunyin.mvp.contract.mine.MyWalletContract
import com.tunyin.mvp.model.mine.MyWalletEntity
import com.tunyin.mvp.model.mine.PayInfoEntity
import com.tunyin.mvp.presenter.mine.MyWalletPresenter
import com.tunyin.ui.adapter.mine.DepositAdapter
import com.tunyin.ui.dialog.EditAmountDialog
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import com.tunyin.utils.WechatUtil
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.greenrobot.eventbus.Subscribe
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set

/**
 * 我的钱包
 */
class MyWalletActivity : BaseInjectActivity<MyWalletPresenter>(), MyWalletContract.View, View.OnClickListener {

    private var payType: String? = ""
    private var selectPosition: Int = -1
    private lateinit var depositAdapter: DepositAdapter
    private val listRb = arrayListOf<RadioButton>()
    private val priceList = ArrayList<MyWalletEntity.RechargeListBean>()


    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun getLayoutId(): Int = R.layout.activity_wallet


    override fun showError(msg: String) {
        hideLoading()
    }

    override fun getMyWalletSuc(myWalletEntity: MyWalletEntity) {
        hideLoading()
        tv_balance.text = myWalletEntity.balance
        priceList.clear()
        priceList.addAll(myWalletEntity.rechargeList)
        depositAdapter.dataList = priceList
        depositAdapter.notifyDataSetChanged()
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))

        tv_protocol.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        tv_title.text = "充值"
        tv_right_title.text = "交易记录"
        tv_right_title.visibility = View.VISIBLE
        tv_right_title.setOnClickListener(this)
        rb_wechat.setOnClickListener(this)
        rb_alipay.setOnClickListener(this)
        rl_wechat.setOnClickListener(this)
        rl_alipay.setOnClickListener(this)
        ly_pay.setOnClickListener(this)
        et_price.setOnClickListener(this)

        depositAdapter = DepositAdapter()
        recycler.layoutManager = GridLayoutManager(mContext, 3)
        depositAdapter.setOnItemClickListener { _, position ->
            et_price.text = ""
            selectPosition = position
            depositAdapter.setDefSelect(position)
        }
        recycler.adapter = depositAdapter
        depositAdapter.dataList = priceList

        showLoading()
        mPresenter.getMyWallet()

//        depositAdapter = DepositAdapter()
//        depositAdapter.setOnItemClickListener { _, position ->
//            depositAdapter.setDefSelect(position)
//        }
//        recycler.layoutManager = GridLayoutManager(mContext, 3)
//        recycler.adapter = depositAdapter

//        priceList.add("100")
//        priceList.add("200")
//        priceList.add("500")
//        priceList.add("1000")
//        priceList.add("2000")
//        priceList.add("5000")
//        depositAdapter.dataList = priceList
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            tv_right_title -> {
                val intent = Intent(mContext, TransactionRecordsActivity2::class.java)
                mContext?.startActivity(intent)
            }

            rb_wechat, rl_wechat -> {
                if (listRb.contains(rb_wechat)) {

                } else {
                    listRb.clear()
                    listRb.add(rb_wechat)
                    rb_wechat.isChecked = true
                    rb_alipay.isChecked = false
                    payType = "wechatPay"
                }
            }
            rb_alipay, rl_alipay -> {
                if (listRb.contains(rb_alipay)) {

                } else {
                    listRb.clear()
                    listRb.add(rb_alipay)
                    rb_alipay.isChecked = true
                    rb_wechat.isChecked = false
                    payType = "aliPay"
                }
            }
            ly_pay -> {
                if (selectPosition == -1 && TextUtils.isEmpty(et_price.text.trim().toString())) {
                    ToastUtils.showToast("请选择金额或是填写金额")
                    return
                }

                if (listRb.size == 0) {
                    ToastUtils.showToast("请选择支付方式")
                    return
                }

                if (TextUtils.equals("wechatPay", payType.toString())) {
                    // ToastUtils.showToast("微信支付接入中，请先用支付宝")
                }

                val map = HashMap<String, String>()
                if (!TextUtils.isEmpty(et_price.text.trim().toString()) && et_price.text.trim().toString().toInt() > 0) {
                    map["money"] = et_price.text.trim().toString()
                } else {
                    map["rechargeId"] = depositAdapter.dataList[selectPosition].id
                }
                map["type"] = payType.toString()
//                map["rechargeId"] = depositAdapter.dataList[selectPosition].id
                showLoading()
                mPresenter.getPayInfo(map)

            }

            et_price -> {
                EditAmountDialog(this@MyWalletActivity, EditAmountDialog.OnClickListener { dialog, amount ->
                    et_price.text = amount + "元"

                    selectPosition = -1
                    depositAdapter.setDefSelect(-1)

                    dialog.dismiss()
                }).show()
            }
        }
    }


    override fun getPayInfoSuc(payInfoEntity: PayInfoEntity) {
        hideLoading()
        if (payInfoEntity.payInfoData == null) {
            return
        }
        if (TextUtils.isEmpty(payInfoEntity.payInfoData.alipayData)) {
            var payReq = payInfoEntity.payInfoData
            WechatUtil.getInstance().payOrder(this@MyWalletActivity,
                    payReq.appid,
                    payReq.partnerid,
                    payReq.prepayid,
                    payReq.packages,
                    payReq.noncestr,
                    payReq.timestamp,
                    payReq.sign)
        } else {
            alipay(payInfoEntity.payInfoData.alipayData)
        }
    }

    val SDK_PAY_FLAG = 1
    fun alipay(orderInfo: String) {

        // 订单信息
        val orderInfo = orderInfo
        val payRunnable = Runnable {
            val alipay = PayTask(this@MyWalletActivity)
            val result = alipay.payV2(orderInfo, true)
            val msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }
        val payThread = Thread(payRunnable)
        payThread.start()

    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SDK_PAY_FLAG -> {
                    val payResult = PayResult(msg.obj as Map<String, String>)

                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    val resultInfo = payResult.result// 同步返回需要验证的信息
                    val resultStatus = payResult.resultStatus
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showLoading()
                        mPresenter.getMyWallet()
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(this@PayDemoActivity, "支付成功", Toast.LENGTH_SHORT).show()
//                        finish()
//                        startActivity(PaySucActivity.newIntent(context, totalPrice, orderNo))
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showToast("支付失败")
//                        Toast.makeText(this@PayDemoActivity, "支付失败", Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getMyWallet()
    }

    @Subscribe
    fun onWXPayEvent(event: WXPayEvent?) {
        if (event != null) {
            if (event.isSuccess) {
                ToastUtils.showToast("充值成功")
            } else {
                ToastUtils.showToast("充值失败")
            }
        }
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

}