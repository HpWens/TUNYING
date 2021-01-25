package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.CountDownTimer
import android.os.IBinder
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.base.WebActivity
import com.tunyin.mvp.contract.mine.RegisterContract
import com.tunyin.mvp.model.mine.RegisterEntity
import com.tunyin.mvp.presenter.mine.RegisterPresenter
import com.tunyin.ui.dialog.RegisterSuccessDialog
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 注册
 */
class RegisterActivity : BaseInjectActivity<RegisterPresenter>(), View.OnClickListener, RegisterContract.View {


    private var countDownTimer: CountDownTimer? = null

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun sendMsmSuc(string: String) {
        countDownTimer!!.start()
        hideLoading()
        ToastUtils.showToast(string)
    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun registerData(registerSuccess: Boolean, registerEntity: RegisterEntity) {
        hideLoading()

        RegisterSuccessDialog(mContext, RegisterSuccessDialog.OnClickListener {
            it.dismiss()
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }).show()
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }


    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "注册"
        tv_to_login.setOnClickListener(this)
        rl_send_cde.setOnClickListener(this)
        ly_confirm.setOnClickListener(this)
        ll_agreement.setOnClickListener(this)
        tv_privacy.setOnClickListener(this)

        tv_protocol.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        tv_privacy.paint.flags = Paint.UNDERLINE_TEXT_FLAG

        initCountDownTimer()
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

    override fun onClick(p0: View?) {
        when (p0) {
            tv_to_login -> {
                val intent = Intent(mContext, LoginActivity::class.java)
                mContext?.startActivity(intent)
            }
            ly_confirm -> {
                if (TextUtils.isEmpty(et_code.text)) {
                    ToastUtils.showToast("请输入验证码")
                    return
                }
                if (TextUtils.isEmpty(et_nickname.text)) {
                    ToastUtils.showToast("请输入昵称")
                    return
                }
                if (TextUtils.isEmpty(et_pwd.text)) {
                    ToastUtils.showToast("请输入密码")
                    return
                }
                showLoading()

                mPresenter.register(et_phone.text.trim().toString(),
                        et_pwd.text.trim().toString(),
                        et_nickname.text.trim().toString(),
                        et_code.text.trim().toString())
            }

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
                hideKeyboard(p0?.applicationWindowToken)
//                countDownTimer!!.start()
                mPresenter.sendMsm(et_phone.text.toString(), "1")

            }

            ll_agreement -> {
                startActivity(mContext?.let { WebActivity.newIntent(it, "用户协议", "http://api.itunyin.com/api/html/h5?type=useragreement") })
            }

            tv_privacy -> {
                startActivity(mContext?.let { WebActivity.newIntent(it, "隐私协议", "https://api.itunyin.com/api/html/h5?type=policy") })
            }
        }
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, RegisterActivity::class.java)
            return intent
        }
    }
}