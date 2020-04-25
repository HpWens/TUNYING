package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.ResetPasswordContract
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.presenter.mine.ResetPasswordPresenter
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * create by wangrongchao
 * on 2019/11/2
 *忘记密码
 **/
class ResetPasswordActivity : BaseInjectActivity<ResetPasswordPresenter>(), ResetPasswordContract.View, View.OnClickListener {

    override fun changePasswordSus(string: String) {
        hideLoading()
        ToastUtils.showToast("修改密码成功")
        finish()
    }

    private var countDownTimer: CountDownTimer? = null

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)

    override fun sendMsmSuc(string: String) {
        hideLoading()
        countDownTimer!!.start()
        ToastUtils.showToast(string)
    }


    override fun showError(msg: String) {
        hideLoading()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_reset_password
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "修改密码"
        rl_send_cde.setOnClickListener(this)
        ly_confirm.setOnClickListener(this)

        et_phone.setText("+86 ${SelfBean.instance.phone}")

        initCountDownTimer()
    }

    override fun onClick(p0: View?) {
        when (p0) {
            rl_send_cde -> {
                if (TextUtils.isEmpty(SelfBean.instance.phone)) {
                    ToastUtils.showToast("未设置手机号码")
                    return
                }
                showLoading()
                mPresenter.sendMsm(SelfBean.instance.phone, "3")
            }

            ly_confirm -> {
                if (TextUtils.isEmpty(et_code.text)) {
                    ToastUtils.showToast("请输入验证码")
                    return
                }
                if (TextUtils.isEmpty(et_pwd.text)) {
                    ToastUtils.showToast("请输入密码")
                    return
                }
                showLoading()
                mPresenter.changePassword(et_pwd.text.trim().toString(),
                        et_code.text.trim().toString())
            }
        }
    }

    private fun initCountDownTimer() {
        countDownTimer = object : CountDownTimer(60000L, 1000L) {
            override fun onFinish() {
                rl_send_cde.isClickable = true
                tv_send_code.text = "获取验证码"
                tv_send_code.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTick(paramAnonymousLong: Long) {
                rl_send_cde.isClickable = false
                tv_send_code.setTextColor(resources.getColor(R.color.white))
                tv_send_code.text = (paramAnonymousLong / 1000L).toString() + "秒后获取"
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, ForgetPwdActivity::class.java)
            return intent
        }
    }
}