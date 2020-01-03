package com.tunyin.ui

import android.content.Intent
import android.os.Handler
import android.text.TextUtils
import com.tunyin.MainActivity
import com.tunyin.R
import com.tunyin.base.BaseActivity
import com.tunyin.mvp.model.SelfBean
import com.tunyin.ui.activity.index.GuideActivity
import com.tunyin.ui.activity.mine.LoginActivity
import com.tunyin.utils.AccountHelper
import com.tunyin.utils.StatusBarUtil

/**
 * 启动页
 */
class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initWidget() = StatusBarUtil.setTransparent(this)

    override fun loadData() {
        super.loadData()
        Handler().postDelayed({
            //第一次进入app,那么应该启动轮播页
//            if (AccountHelper.isFirstEnterApp()) {
            //                if (true) {
//                startActivity(Intent(this@SplashActivity, GuideActivity::class.java))
//            } else {
            //不是第一次进入app,如果已经登录，那么跳到首页
//                if (AccountHelper.hasLogin() && !TextUtils.isEmpty(AccountHelper.getPhone())) {
//            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                } else {
            //未登录，跳到登录页面
//                    startActivity(LoginActivity.newInstance(context))

//                }
//            }

            if (SelfBean.instance.isEnter) {
                if (TextUtils.isEmpty(SelfBean.instance.token)) {
                    startActivity(LoginActivity.newInstance(mContext))
                } else {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }
            } else {
                startActivity(GuideActivity.newInstance(mContext))
            }

            finish()
        }, 2000)
    }
}