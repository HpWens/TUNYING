package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import com.tunyin.R
import com.tunyin.base.BaseActivity
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 我的消息
 */
class MyMsgActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_my_msg
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "我的消息"
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, MyMsgActivity2::class.java)
            return intent
        }
    }
}