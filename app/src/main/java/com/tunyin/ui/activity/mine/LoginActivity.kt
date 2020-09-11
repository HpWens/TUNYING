package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.tunyin.MainActivity
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.LoginContract
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.mine.LoginEntity
import com.tunyin.mvp.presenter.mine.LoginPresenter
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录
 */
class LoginActivity : BaseInjectActivity<LoginPresenter>(), View.OnClickListener, LoginContract.View {


    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initWidget() {
        // 作用：我的账户的状态栏延伸至最顶部
//        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.color_67D0F0))
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
//        StatusBarUtil.setLightMode(this)
        ly_login.setOnClickListener(this)
        tv_forget_pwd.setOnClickListener(this)
        iv_clear.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        iv_qq.setOnClickListener(this)
        iv_weibo.setOnClickListener(this)
        iv_wechat.setOnClickListener(this)

        tv_login.post {
            var gradient = LinearGradient(0F, 0F, tv_login.width.toFloat(), tv_login.height.toFloat(), Color.parseColor("#5DE0F1"), Color.parseColor("#908AEE"), Shader.TileMode.CLAMP)
            tv_login.paint.shader = gradient
            tv_login.invalidate()
        }
    }


    override fun onClick(p0: View?) {
        when (p0) {
            ly_login -> {

                if (et_phone.text.isEmpty()) {
                    ToastUtils.showToast("请输入手机号")
                    return
                }
                if (et_pwd.text.isEmpty()) {
                    ToastUtils.showToast("请输入密码")
                    return
                }

//                mPresenter.login("15080311579","12345678")
                hideKeyboard(p0?.applicationWindowToken)
                showLoading()
                mPresenter.login(et_phone.text.toString(), et_pwd.text.toString())

            }
            iv_clear -> {
                et_phone.text.clear()
            }
            tv_register -> {
                startActivity(RegisterActivity.newInstance(mContext))
//                finish()
            }

            tv_forget_pwd -> {
                startActivity(ForgetPwdActivity.newInstance(mContext))

            }

            iv_wechat -> {
                // ToastUtils.showToast("还没开放")

            }

            iv_weibo -> {
                ToastUtils.showToast("还没开放")

            }

            iv_qq -> {
                ToastUtils.showToast("还没开放")

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

//    override fun showError(msg: String) {
//        hideLoading()
//    }


    override fun showError(msg: String) {
        super<LoginContract.View>.showError(msg)
        hideLoading()
    }

    override fun showLoginData(loginEntity: LoginEntity) {
        hideLoading()
//        AccountHelper.saveToken(loginEntity.token)
//        AccountHelper.saveToken(loginEntity.token)
        SelfBean.instance.token = loginEntity.token
        SelfBean.instance.userId = loginEntity.userId
        SelfBean.instance.username = loginEntity.username
        SelfBean.instance.nickName = loginEntity.nickName
        SelfBean.instance.headUrl = loginEntity.headUrl
        SelfBean.instance.phone = loginEntity.phone

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        finish()
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }
}