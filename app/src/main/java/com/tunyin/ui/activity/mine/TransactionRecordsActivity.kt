package com.tunyin.ui.activity.mine

import com.tunyin.R
import com.tunyin.base.BaseActivity
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 交易记录
 */
class TransactionRecordsActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_transaction_records
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "交易记录"

    }
}