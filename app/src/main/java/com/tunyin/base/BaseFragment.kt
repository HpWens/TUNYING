package com.tunyin
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.trello.rxlifecycle2.components.support.RxFragment
import com.tunyin.mvp.model.Event
import com.tunyin.utils.EventBusUtil

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/8
 * desc: 基础Fragment
 *
 */
abstract class BaseFragment : RxFragment() {

    protected var mView: View? = null
    protected var mActivity: Activity? = null
    protected var mInflater: LayoutInflater? = null
    protected var mContext: Context? = null
    // 标记初始化已经完成
    protected var mIsPrepared: Boolean = false
    // 标志Fragment是否可见
    protected var mIsVisible: Boolean = true

    override fun onAttach(context: Context?) {
        mActivity = context as? Activity
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//todo
//        if (mView != null) {
//            val parent = mView?.parent as ViewGroup
//            parent.removeView(mView)
//        } else {
            mView = inflater.inflate(getLayoutId(), container, false)
            mActivity = getSupportActivity()
            mContext = mActivity
            this.mInflater = inflater
//        }

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isRegisterEventBus()) {
            EventBusUtil.register(this)
        }
        initInject()
        initPresenter()
        initVariables()
        initWidget()
        initSetListener()
        finishCreateView(savedInstanceState)
        initDatas()
    }

    protected open fun initSetListener() {
    }

    protected open fun lazyLoadData() {
    }

    protected open fun initDatas() {
        loadData()
    }

    open fun finishCreateView(state: Bundle?) {
        mIsPrepared = true
        lazyLoad()
    }

    /**
     * 分离
     */
    override fun onDetach() {
        this.mActivity = null
        super.onDetach()
    }

    /**
     * 解绑EventBus
     */
    override fun onDestroy() {
        super.onDestroy()
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this)
        }
    }

    /**
     * 初始化RV
     */
    protected open fun initRecyclerView() {
    }

    /**
     * 初始化刷新
     */
    @SuppressLint("CheckResult")
    protected open fun initRefreshLayout() {
    }

    /**
     * 清除数据
     */
    protected open fun clearData() {
    }

    /**
     * 初始化Presenter
     */
    protected open fun initPresenter() {}

    /**
     * 初始化变量
     */
    open fun initVariables() {}

    /**
     * 懒加载
     */
    protected open fun lazyLoad() {
        if (!mIsPrepared || !mIsVisible) return
        lazyLoadData()
        mIsPrepared = false
    }

    protected open fun onInvisible() {
    }

    /**
     * 接收事件
     *
     * @param event 事件
     */
    protected open fun receiveEvent(event: Event<*>) {

    }

    /**
     * 加载数据
     */
    protected open fun loadData() {}

    /**
     * 注入dagger2依赖
     */
    protected open fun initInject() {
    }

    protected open fun finishTask() {
    }

    /**
     * 布局
     * @return int
     */
    abstract fun getLayoutId(): Int

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    open fun initWidget() {
    }

    protected open fun onVisible() {
        lazyLoad()
    }

    /**
     * Fragment数据的懒加载
     * @param isVisibleToUser
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            mIsVisible = true
            onVisible()
        } else {
            mIsVisible = false
            onInvisible()
        }
    }

    /**
     * 获取Activity
     * @return FragmentActivity
     */
    fun getSupportActivity(): FragmentActivity = super.getActivity() as FragmentActivity

    /**
     * 获取ApplicationContext 信息
     * @return Context
     */
    fun getApplicationContext(): Context? =
            if (this.mContext == null) {
                if (activity == null) {
                    null
                } else {
                    activity!!.application
                }
            } else {
                this.mContext?.applicationContext
            }

    /**
     * 隐藏View
     * @param views 视图
     */
    fun gone(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.GONE
            }
        }
    }

    /**
     * 显示View
     * @param views 视图
     */
    fun visible(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected open fun isRegisterEventBus(): Boolean {
        return false
    }

    inner class BaseStagesTypeAdapter(fm: FragmentManager, private val mTitles: List<String>, private val mFragments: List<Fragment>) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            mFragments.let {
                return it[position]
            }
        }

        override fun getCount(): Int {
            return mTitles.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitles[position]
        }
    }
}