package com.tunyin.base

import com.tunyin.*
import com.tunyin.mvp.model.SelfBean.Companion.instance
import com.tunyin.ui.activity.mine.LoginActivity.Companion.newInstance
import com.tunyin.utils.AnimationUtils.animateView
import kotlinx.android.synthetic.main.item_loading.*
import javax.inject.Inject

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/8
 * desc: 基础注入Activity
 *
 */
abstract class BaseInjectActivity<T : BaseContract.BasePresenter<*>> : BaseActivity(), BaseContract.BaseView {

    @Inject
    lateinit var mPresenter: T

    protected val activityModule: ActivityModule get() = ActivityModule(this)

    protected val activityComponent: ActivityComponent
        get() = DaggerActivityComponent.builder()
                .apiComponent(App.instance.mApiComponent)
                .activityModule(activityModule)
                .build()

    override fun onDestroy() {
        mPresenter.detachView()
        App.instance.removeActivity(this)
        super.onDestroy()
    }

    override fun showError(msg: String) {
//        if (loading_progress_bar != null) animateView(loading_progress_bar, false, 0)

        if (msg != null && (msg.contains("token") || msg.contains("Token"))) {
            instance.token = ""
            startActivity(newInstance(this))
        }
    }

    override fun complete() {
    }

    override fun showLoading() {
        if (loading_progress_bar != null) animateView(loading_progress_bar, true, 400)

    }

    override fun hideLoading() {
        if (loading_progress_bar != null) animateView(loading_progress_bar, false, 0)

    }

    override fun showEmptyState() {
    }
}