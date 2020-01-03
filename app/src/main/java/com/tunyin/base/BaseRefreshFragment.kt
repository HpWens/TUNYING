package com.tunyin.base

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tunyin.BaseContract
import com.tunyin.BaseInjectFragment
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.utils.AnimationUtils
import com.tunyin.utils.AppUtils
import kotlinx.android.synthetic.main.item_loading.*

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/10
 * desc: 基础刷新Fragment
 *
 */
abstract class BaseRefreshFragment<T : BaseContract.BasePresenter<*>, K> : BaseInjectFragment<T>(), SwipeRefreshLayout.OnRefreshListener {
    protected var mRecycler: RecyclerView? = null
    protected var mRefresh: SwipeRefreshLayout? = null
    protected var mIsRefreshing = false
    protected var mList = mutableListOf<K>()

    override fun initRefreshLayout() {
        mRefresh?.let {
            it.setColorSchemeResources(R.color.colorPrimary)
            mRecycler?.post {
                it.isRefreshing = true
                lazyLoadData()
            }
            it.setOnRefreshListener(this)
        }
    }

    override fun onRefresh() {
        clearData()
        lazyLoadData()
    }

    override fun clearData() {
        mIsRefreshing = true
    }

    override fun finishCreateView(state: Bundle?) {
        mRefresh = mView?.findViewById(R.id.refresh) as SwipeRefreshLayout?
        mRecycler = mView?.findViewById(R.id.recycler) as RecyclerView?
        mIsPrepared = true
        lazyLoad()
    }

    override fun lazyLoad() {
        if (!mIsPrepared || !mIsVisible) return
        initRefreshLayout()
        initRecyclerView()
        mRefresh ?: lazyLoadData()
        mIsPrepared = false
    }

    override fun complete() {
        AppUtils.runOnUIDelayed({
            mRefresh?.let { it.isRefreshing = false }
        }, 0)
        if (mIsRefreshing) {
            mList.clear()
            clear()
            ToastUtils.showToast("刷新成功")
        }
        mIsRefreshing = false
    }

    override fun showLoading() {
        if (loading_progress_bar != null) AnimationUtils.animateView(loading_progress_bar, true, 400)

    }

    override fun hideLoading() {
        if (loading_progress_bar != null) AnimationUtils.animateView(loading_progress_bar, false, 0)

    }

    override fun showEmptyState() {
    }

    protected open fun clear() {
    }

    override fun initWidget() {
    }
}