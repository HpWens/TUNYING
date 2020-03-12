package com.tunyin

import android.content.Intent
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.github.hymanme.tagflowlayout.bean.TagBean
import com.google.android.material.navigation.NavigationView
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.app.MainContract
import com.tunyin.mvp.model.MessageEvent
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.TabEntity
import com.tunyin.mvp.presenter.app.MainPresenter
import com.tunyin.myservice.Music
import com.tunyin.myservice.OnPlayerEventListener
import com.tunyin.ui.activity.MyRankActivity
import com.tunyin.ui.activity.mine.*
import com.tunyin.ui.fragment.discovery.DiscoveryFragment
import com.tunyin.ui.fragment.index.IndexFragment
import com.tunyin.ui.fragment.mine.MineFragment
import com.tunyin.ui.fragment.purchased.PurchasedFragment
import com.tunyin.utils.ImageUtil
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_nav_left.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseInjectActivity<MainPresenter>(), OnTabSelectListener, MainContract.View, View.OnClickListener, OnPlayerEventListener {
    override fun onChange(music: Music?) {
    }

    override fun onPlayerStart() {
    }

    override fun onPlayerPause() {
    }

    override fun onPublish(progress: Int) {
    }

    override fun onBufferingUpdate(percent: Int) {
    }

    private val CHOOSE_REQUEST_CODE = 1
    private var exitTime = 0L
    private var mCurrentPos = -1
    private val numbers = ArrayList<Int>()
    private var mFragments = mutableListOf<Fragment>()
    private var mTabEntities = ArrayList<CustomTabEntity>()
    private lateinit var mTagBean: TagBean
    private var title: String? = null

    private val mTitles = arrayOf("首页", "发现", "已购", "我的")

    internal lateinit var ivIvatar: ImageView
    internal lateinit var tvNickName: TextView
    internal lateinit var tvMsg: TextView
    internal lateinit var tvFrined: TextView
    internal lateinit var tvCollect: TextView
    internal lateinit var vipLayout: LinearLayout

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.tab_home_unselect, R.mipmap.tab_discovery_unselect, R.mipmap.tab_perchand_unselect, R.mipmap.tab_mine_ubselect)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_home, R.mipmap.tab_discovery, R.mipmap.tab_perchand, R.mipmap.tab_mine)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)

    override fun initWidget() {
        EventBus.getDefault().register(this)
        super.initWidget()
        // 作用：我的账户的状态栏延伸至最顶部
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        StatusBarUtil.setLightMode(this)
        MyAudioPlayer.get().addOnPlayEventListener(this)

        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }


        bottom_main.setTabData(mTabEntities)
        bottom_main.setOnTabSelectListener(this)

        mFragments = Arrays.asList(
                IndexFragment.newInstance(),
                DiscoveryFragment.newInstance(),
                PurchasedFragment.newInstance(),
                MineFragment.newInstance()
        )
        switchFragmentIndex(0)
        nav_left.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                val id = p0.itemId
                drawer_layout.closeDrawer(GravityCompat.START)
                val mString: String? = null
                when (id) {
                    R.id.nav_order -> {
                        startActivity(MyOrderActivity.newInstance(mContext))

                    }
                    R.id.nav_timer -> {
                        drawer_layout.closeDrawer(GravityCompat.START)
                        startActivity(MyMsgActivity.newInstance(mContext))
                    }
                    R.id.nav_history -> {
                        startActivity(MyCollectActivity.newInstance(mContext))
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    R.id.nav_setting -> {

                    }
                    R.id.nav_logout -> {
                        SelfBean.instance.token = ""
                        startActivity(LoginActivity.newInstance(mContext))
                        finish()
                    }
                }
                return true
            }
        })


        ivIvatar = nav_left.getHeaderView(0).findViewById(R.id.iv_avatar)
        tvNickName = nav_left.getHeaderView(0).findViewById(R.id.tv_name)
        tvMsg = nav_left.getHeaderView(0).findViewById(R.id.tv_msg)
        tvFrined = nav_left.getHeaderView(0).findViewById(R.id.tv_friend)
        tvCollect = nav_left.getHeaderView(0).findViewById(R.id.tv_collect)

        vipLayout = nav_left.getHeaderView(0).findViewById(R.id.ly_vip)

        vipLayout.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            val intent = Intent(this, TunYinVIPDepositActivity::class.java)
            this.startActivity(intent)
        }

        ivIvatar.setOnClickListener {
            ToastUtils.showToast("我是头像")
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        tvNickName.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        tvMsg.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            // startActivity(MyMsgActivity.newInstance(mContext))
            val intent = Intent(this@MainActivity, TunYinVIPDepositActivity::class.java)
            startActivity(intent)
        }

        tvFrined.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
            val intent = Intent(this@MainActivity, MyRankActivity::class.java)
            startActivity(intent)
        }
        tvCollect.setOnClickListener {
            // startActivity(MyCollectActivity.newInstance(mContext))
            drawer_layout.closeDrawer(GravityCompat.START)
            val intent = Intent(this@MainActivity, MyVoucherActivity::class.java)
            startActivity(intent)
        }
        tvNickName.text = SelfBean.instance.nickName
//        ImageUtil.load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573299081266&di=3539ee4863f614576acd82dec4b0bcb7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201605%2F09%2F20160509144239_xSTPX.thumb.700_0.jpeg").isCircle.on(ivIvatar)

        ImageUtil.load(SelfBean.instance.headUrl).isCircle.on(ivIvatar)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        switchDrawer()
    }

    fun switchDrawer(): Unit {
        if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
            drawer_layout.closeDrawer(Gravity.LEFT)
        } else {
            drawer_layout.openDrawer(Gravity.LEFT)
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            iv_avatar -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    override fun loadData() {
        numbers.add(432)
        numbers.add(1228)
        mPresenter.getRegionTagTypeBean(numbers) // 初始值默认为中学
    }

    override fun onTabSelect(position: Int) = switchFragmentIndex(position)


    override fun onTabReselect(position: Int) {}

    private fun switchFragmentIndex(index: Int) {
        // 特别注意，fragment重叠问题，mCurrentPos是上一个fragment,index是当前fragment
        supportFragmentManager
                .beginTransaction()
                .apply {
                    if (mCurrentPos != -1)
                        hide(mFragments[mCurrentPos])
                    if (!mFragments[index].isAdded) {
                        add(R.id.fl_content, mFragments[index])
                    }
                    show(mFragments[index]).commit()
                    mCurrentPos = index
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                CHOOSE_REQUEST_CODE -> {
//                    val bundle = data?.extras
//                    bundle?.let {
//                        numbers.clear()
//                        numbers.add(it.getInt("tagId"))
//                        numbers.add(it.getInt("nextId"))
//                        mPresenter.getRegionTagTypeBean(numbers)
//                    }
//                }
//            }
//        }
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            exitApp()
//        }
//        return true
//    }

    override fun onBackPressed() {
        exitApp();
        // super.onBackPressed()
    }

    private fun exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showToast("再按一次退出应用")
            exitTime = System.currentTimeMillis()
        } else {
            App.instance.exitApp()
        }
    }
}

