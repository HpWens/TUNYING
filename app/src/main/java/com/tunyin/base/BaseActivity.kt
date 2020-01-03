package com.tunyin.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.tunyin.App
import com.tunyin.MyPlayService
import com.tunyin.R
import com.tunyin.utils.AppUtils
import com.tunyin.utils.EventBusUtil
import com.tunyin.utils.StatusBarUtil


/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/7
 * desc: 基础BaseActivity
 *
 */
abstract class BaseActivity : RxAppCompatActivity() {

    protected var mContext: Context? = null
    protected var mToolbar: Toolbar? = null
    protected open val mBack = true // 是否返回
    protected var myhandler: Handler? = null
    protected var playService: MyPlayService? = null
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (isRegisterEventBus()) {
            EventBusUtil.register(this)
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mToolbar = findViewById(R.id.toolbar) as? Toolbar
        mContext = this
        initStatusBar()
        initInject()
        initPresenter()
        initVariables()
        App.instance.addActivity(this)
        mToolbar?.let {
            // 初始化Toolbar
            // initToolbar()
            // 组件化Toolbar
            setSupportActionBar(it)
            if (mBack) it.setNavigationOnClickListener { finish() }
        }
        initWidget()
        loadDatas()

        bindService()
    }

    open fun initVariables() {}

    open fun loadDatas() {
        loadData()
    }

    open fun initPresenter() {
    }

    open fun initInject() {
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        // 如果用以下这种做法则不保存状态，再次进来的话会显示默认tab
        // 总是执行这句代码来调用父类去保存视图层的状态
        // super.onSaveInstanceState(outState);
    }

    open fun initToolbar() {
        if (mBack) mToolbar?.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
    }

    private fun initStatusBar() = StatusBarUtil.setColorNoTranslucent(mContext as Activity, AppUtils.getColor(R.color.colorPrimary))

    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    open fun initWidget() {}

    /**
     * 加载数据
     */
    open fun loadData() {}

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this)
        }
        App.instance.removeActivity(this)
    }

    fun initRecyclerView() {}

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
     * 显示View 不占位置
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
     * 显示View
     * @param views 视图
     */
    fun inVisible(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.INVISIBLE
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

    private fun bindService() {
        val intent = Intent()
        intent.setClass(this, MyPlayService::class.java)
        serviceConnection = PlayServiceConnection()
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }


    private inner class PlayServiceConnection : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            playService = (service as MyPlayService.PlayBinder).getService()
            onServiceBound()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.e(javaClass.simpleName, "service disconnected")
        }
    }

    protected fun onServiceBound() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // super.onActivityResult(requestCode, resultCode, data)
        val fragmentManager = supportFragmentManager
        for (indext in 0 until fragmentManager.fragments.size) {
            val fragment = fragmentManager.fragments[indext] //找到第一层Fragment
            fragment?.let {
                if (data != null) {
                    handleResult(it, requestCode, resultCode, data)
                }
            } ?: Log.w("", "Activity result no fragment exists for index: 0x" + Integer.toHexString(requestCode))
        }
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val fragmentManager = supportFragmentManager
//        for (indext in 0 until fragmentManager.fragments.size) {
//            val fragment = fragmentManager.fragments[indext] //找到第一层Fragment
//            fragment?.let { handleResult(it, requestCode, resultCode, data) }
//                    ?: Log.w("", "Activity result no fragment exists for index: 0x" + Integer.toHexString(requestCode))
//        }
//    }

    private fun handleResult(fragment: Fragment, requestCode: Int, resultCode: Int, data: Intent) {
        fragment.onActivityResult(requestCode, resultCode, data)//调用每个Fragment的onActivityResult
//        Log.e(FragmentActivity.TAG, "MyBaseFragmentActivity")
        val childFragment = fragment.getChildFragmentManager().getFragments() //找到第二层Fragment
        if (childFragment != null)
            for (f in childFragment!!)
                if (f != null) {
                    handleResult(f!!, requestCode, resultCode, data)
                }
//        if (childFragment == null)
//            Log.e(FragmentActivity.TAG, "MyBaseFragmentActivity1111")
    }


}
