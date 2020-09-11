package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.ForgetPwdContract
import com.tunyin.mvp.presenter.mine.ForgetPwdPresenter
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * create by wangrongchao
 * on 2019/11/2
 *忘记密码
 **/
class ForgetPwdActivity : BaseInjectActivity<ForgetPwdPresenter>(), ForgetPwdContract.View, View.OnClickListener {


    private var countDownTimer: CountDownTimer? = null

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun sendMsmSuc(string: String) {
        hideLoading()
        countDownTimer!!.start()
        ToastUtils.showToast(string)
    }

    override fun forgetPwdData(string: String) {
        hideLoading()
        ToastUtils.showToast(string)
//        val intent = Intent(mContext, LoginActivity::class.java)
//        mContext?.startActivity(intent)
        finish()
    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_forget_pwd
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "忘记密码"
        rl_send_cde.setOnClickListener(this)
        ly_confirm.setOnClickListener(this)

        initCountDownTimer()
    }

    override fun onClick(p0: View?) {
        when (p0) {
            rl_send_cde -> {
                if (TextUtils.isEmpty(et_phone.text)) {
                    ToastUtils.showToast("请输入手机号")
                    return
                }

                if (et_phone.length() != 11) {
                    ToastUtils.showToast("情输入11位手机号")
                    return
                }

                showLoading()
                mPresenter.sendMsm(et_phone.text.toString(), "2")


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

                if (TextUtils.isEmpty(et_pwd2.text)) {
                    ToastUtils.showToast("请再次输入新密码")
                    return
                }

                if (et_pwd2.text != et_pwd.text) {
                    ToastUtils.showToast("两次输入的密码不一致，请重新输入")
                    return
                }

                showLoading()
                mPresenter.forgetPwd(et_phone.text.trim().toString(),
                        et_pwd.text.trim().toString(),
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