package com.tunyin

import com.tunyin.App
import com.tunyin.utils.AnimationUtils
import kotlinx.android.synthetic.main.item_loading.*
import javax.inject.Inject

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/8
 * desc: 基础InjectFragment
 *
 */
abstract class BaseInjectFragment<T : BaseContract.BasePresenter<*>> : BaseFragment(), BaseContract.BaseView {

    @Inject
    lateinit var mPresenter: T

    protected val fragmentModule: FragmentModule get() = FragmentModule(this)

    protected val fragmentComponent: FragmentComponent
        get() = DaggerFragmentComponent.builder()
                .apiComponent(App.instance.mApiComponent)
                .fragmentModule(fragmentModule)
                .build()

    override fun showError(msg: String) {
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun complete() {
    }

    override fun showLoading() {
        if (loading_progress_bar != null) AnimationUtils.animateView(loading_progress_bar, true, 400)

    }

    override fun hideLoading() {
        if (loading_progress_bar != null) AnimationUtils.animateView(loading_progress_bar, false, 0)

    }

    override fun showEmptyState() {
    }
}