package com.tunyin.ui.activity.index

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.text.format.DateUtils
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.tunyin.*
import com.tunyin.base.BaseInjectActivity
import com.tunyin.constants.Constants
import com.tunyin.mvp.contract.index.PlayerContract
import com.tunyin.mvp.model.Event
import com.tunyin.mvp.model.FlyoutTabEntity
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.index.CreateOrderEntity
import com.tunyin.mvp.model.index.MusicEntity
import com.tunyin.mvp.presenter.index.PlayerPresenter
import com.tunyin.myservice.Music
import com.tunyin.myservice.OnPlayerEventListener
import com.tunyin.ui.activity.mine.MyWalletActivity
import com.tunyin.ui.adapter.PagerAdapter
import com.tunyin.ui.dialog.ShareDialog
import com.tunyin.ui.fragment.index.PlayerCommentFragment
import com.tunyin.ui.fragment.index.PlayerDetailFragment
import com.tunyin.ui.fragment.index.PlayerDirectoryFragment
import com.tunyin.utils.*
import kotlinx.android.synthetic.main.coordinator_layout.*
import java.util.*


class PlayerActivity : BaseInjectActivity<PlayerPresenter>(), PlayerContract.View, View.OnClickListener, OnTabSelectListener, ViewPager.OnPageChangeListener, SeekBar.OnSeekBarChangeListener, OnPlayerEventListener {
    private val UPDATE_PROGRESS = 0

    private var mMusicId: String? = null
    private var adapter: PagerAdapter? = null
    private var mListeningTime: String? = Constants.TRY_LISTEN.toString()
    private var isTry: Boolean = false
    private var tryEnd: Boolean? = false
    private var mLastProgress: Int = 0
    private var isDraggingProgress: Boolean = false
    private var money: String? = null
    private var isListen: Boolean? = false
    private var isBuyCatlog: Boolean? = false
    private var priceSingle: String? = null
    private var priceAll: String? = null
    private var payAllThemeId: String? = ""
    private var mMusicEntity: MusicEntity? = null
    private var mAutoPlay: Boolean = true

    // 是否收藏
    private var isCollected: Boolean = false

    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
//        LogUtils.d("--------progress----" + "hello")
        if (Math.abs(progress - mLastProgress) >= DateUtils.SECOND_IN_MILLIS) {
            progressTv.text = formatTime(progress.toLong())
//            LogUtils.d("----progressTvprogressTv-2---" + (MyAudioPlayer.get().audioPosition.toInt()))
            mLastProgress = progress
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        if (p0 == progressSb) {
            isDraggingProgress = true
        }
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (seekBar == progressSb) {
            isDraggingProgress = false
//            if (isListen!! || isBuyCatlog!!) {
            if (isListen!!) {
                if (MyAudioPlayer.get().isPlaying || MyAudioPlayer.get().isPausing) run {
                    val progress = seekBar?.getProgress()
                    MyAudioPlayer.get().seekTo(progress!!)
                }
            } else {
                ToastUtils.showToast("当前为试听，请先购买")
            }
        }
    }

    override fun onChange(music: Music?) {
    }

    override fun onPlayerStart() {
    }

    override fun onPlayerPause() {

    }

    override fun showCancelCollectSuccess() {
        ToastUtils.showToast("取消收藏")
        isCollected = false
        iv_collect.setImageResource(R.mipmap.collect_white)
    }

    override fun showAddCollectSuccess() {
        ToastUtils.showToast("收藏成功")
        isCollected = true
        iv_collect.setImageResource(R.mipmap.collect_red)
    }

    override fun onPublish(progress: Int) {
        if (MyAudioPlayer.get().duration.toString().length > 7) {
            MyAudioPlayer.get().pausePlayer()
            getMusic()
            return
        }

        durationTv.text = formatTime(MyAudioPlayer.get().duration)
        progressSb.setMax(MyAudioPlayer.get().duration.toInt())

        if (progressSb.progress == progressSb.max) {
            play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
        }

        LogUtils.d("-----progressSb------" + "max=" + progressSb.max + "-----current=" + progressSb.progress)

        var duration = MyAudioPlayer.get().duration.toInt()
//        LogUtils.d("-----MyAudioPlayer----duration-2--" + duration)
        if (!isDraggingProgress) {
            progressSb.progress = progress
        }

//        if (isListen!! || isBuyCatlog!!) {
        if (!isListen!!) {
            if (mListeningTime == formatSecond(MyAudioPlayer.get().audioPosition.toInt())) {
                MyAudioPlayer.get().playPauseForTry(mListeningTime)
                progressTv.text = formattime((MyAudioPlayer.get().audioPosition.toInt()))
//                if (musicControl!!.isPlaying) {
                play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
//                    musicControl?.play()

                val builder = AlertDialog.Builder(this)
                builder.setTitle("提示")
                builder.setMessage("试听结束，请购买！")
                builder.setPositiveButton("去购买",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            //点击“知道了”的后续业务逻辑
//                            ToastUtils.showToast("去购买待定")
                            pay("2")//单首歌
                        })
                builder.setNegativeButton("取消",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                        })
                val dialog = builder.create()

                try {
                    dialog.show()
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mContext!!.resources.getColor(R.color.black))
                    dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(mContext!!.resources.getColor(R.color.black))
                } catch (e: Exception) {
                }
                tryEnd = true
            } else {
                if (!tryEnd!!) {
//                    LogUtils.d("----progressTvprogressTv----" + (MyAudioPlayer.get().audioPosition.toInt()))
                    progressTv.text = formattime((MyAudioPlayer.get().audioPosition.toInt()))
                }
            }
        } else {
            progressTv.text = formattime((MyAudioPlayer.get().audioPosition.toInt()))
        }
    }

    override fun onBackPressed() {
        if (ly_price.visibility == View.VISIBLE) {
            MyAudioPlayer.get().pausePlayer()
        }
        super.onBackPressed()
    }

    override fun onBufferingUpdate(percent: Int) {
        progressSb.setSecondaryProgress(progressSb.getMax() * 100 / percent)
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context, musicId: String): Intent {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("musicId", musicId)
            return intent
        }
    }

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)

    override fun showMusicData(musicEntity: MusicEntity) {
        hideLoading()
        mMusicEntity = musicEntity
        ImageUtil.load(musicEntity.image).on(image)
        tv_title.text = musicEntity.title
        tv_sub_title.text = musicEntity.content
        isListen = musicEntity.isListen
        isBuyCatlog = musicEntity.isBuyCatlog
        musicEntity.listeningTime?.let {
            mListeningTime = musicEntity.listeningTime
        }
        payAllThemeId = musicEntity.themeId
        money = musicEntity.price
        priceSingle = musicEntity.price
        priceAll = musicEntity.price

        iv_collect.setImageResource(if (isCollected != musicEntity.isCollect.equals("0")) R.mipmap.collect_white else R.mipmap.collect_red)

        if (isBuyCatlog!!) {//购买了全集
            rl_listen_try.visibility = View.GONE
            ly_price.visibility = View.GONE
            isTry = false//不用试听
        } else {
            if (isListen!!) {//购买了单首歌
                isTry = false//不用试听

                ly_price.visibility = View.GONE
                rl_listen_try.visibility = View.GONE
                tv_price_try.text = "免费试听中，只需" + priceAll + "钻石即可收听全剧"
            } else {
                isTry = true//需要试听

                ly_price.visibility = View.VISIBLE
                rl_listen_try.visibility = View.VISIBLE
                tv_price_try.text = "免费试听中，只需" + priceAll + "钻石即可收听全剧"
                tv_price.text = priceSingle
            }
        }

        //MyAudioPlayer.get().reset()
        MyAudioPlayer.get().play(musicEntity.url, isTry, musicEntity.image)
        SelfBean.instance.musicUrl = musicEntity.url
        onChangeImpl()
        progressSb.setProgress(MyAudioPlayer.get().audioPosition.toInt())
        ivIsPlayingTwo()

        if (mAutoPlay) {
            autoPlayHandler.sendEmptyMessage(1)
        }
    }

    private var autoPlayHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg?.what == 1) {
                autoPlay()
            }
        }
    }

    private fun autoPlay() {
        if (MyAudioPlayer.get().isPlaying) return
        play.postDelayed({
            play.performClick()
            play.postDelayed({
                if (!MyAudioPlayer.get().isPlaying) {
                    play.performClick()
                }
            }, 400)
        }, 400)
    }

    private fun onChangeImpl() {
//        progressTv.text = "00:00"
        progressSb.setProgress(MyAudioPlayer.get().audioPosition.toInt())
        progressSb.setSecondaryProgress(0)
        var duration = MyAudioPlayer.get().duration.toInt()
        LogUtils.d("-----MyAudioPlayer----duration1---" + duration)
        progressSb.setMax(MyAudioPlayer.get().duration.toInt())
        mLastProgress = 0
        var str = formatTime(MyAudioPlayer.get().duration)
        LogUtils.d("-----MyAudioPlayer---" + str)
//        durationTv.text = str
        //没办法的骚操作
//        getMusic()
    }


    override fun showError(msg: String) {
        hideLoading()
        if (msg == "钻石不足") {
            val intent = Intent(mContext, MyWalletActivity::class.java)
            mContext?.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.coordinator_layout
    }

    fun getMusic() {
        mMusicId?.let {
            showLoading()
            if (SelfBean.instance.musicHisId == "-1") {
                mPresenter.getMusic(SelfBean.instance.musicHisId)
            } else {
                mPresenter.getMusic(it)
            }
            SelfBean.instance.musicHisId = it
        }
    }

    private lateinit var playerDirectoryFragment: PlayerDirectoryFragment

    override fun initWidget() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
//        StatusBarUtil.setTranslucentForImageView(this, 0, null)
//        StatusBarUtil.setLightMode(this)
        mMusicId = intent.getStringExtra("musicId")
        MyAudioPlayer.get().songId = mMusicId
        MyAudioPlayer.get().addOnPlayEventListener(this)
        progressSb.setOnSeekBarChangeListener(this)

        play.setOnClickListener(this)
        tv_right_title.setOnClickListener(this)
        iv_collect.setOnClickListener(this)
        tv_to_pay.setOnClickListener(this)
        tv_to_pay_try.setOnClickListener(this)

        val mTabEntities = ArrayList<CustomTabEntity>()
        val fragments = ArrayList<BaseFragment>()

        mTabEntities.add(FlyoutTabEntity("详情"))
        mTabEntities.add(FlyoutTabEntity("目录"))
        mTabEntities.add(FlyoutTabEntity("评论"))

        playerDirectoryFragment = PlayerDirectoryFragment.newInstance(mMusicId.toString())
        playerDirectoryFragment.setChangeMusicListener(object : PlayerDirectoryFragment.OnChangeMusicListener {
            override fun changeMusic(id: String) {
                changeMusicById(id)
            }
        })
        fragments.add(PlayerDetailFragment.newInstance(mMusicId.toString()))
        fragments.add(playerDirectoryFragment)
        fragments.add(PlayerCommentFragment.newInstance(mMusicId.toString()))

        mTabLayout.setTabData(mTabEntities)
        mTabLayout.setOnTabSelectListener(this)

        adapter = PagerAdapter(supportFragmentManager, fragments)
        mViewPager.adapter = adapter
        mViewPager.addOnPageChangeListener(this)

        mToolbar!!.setNavigationOnClickListener { onBackPressed() }

        getMusic()
    }

    private fun changeMusicById(id: String) {
        mMusicId = id
        MyAudioPlayer.get().songId = mMusicId
        mListeningTime = Constants.TRY_LISTEN.toString()
        tryEnd = false
        play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_pause))
        handler.removeCallbacksAndMessages(null)
        //                musicControl?.clear()
        //                unbindService(conn)
        showLoading()
        mPresenter.getMusic(id)
        SelfBean.instance.musicHisId = id

        // 更新详情页
        var updateEvent: Event<String> = Event(101, id)
        EventBusUtil.sendEvent(updateEvent)
    }

    fun prevPlay(v: View) {
        // mMusicId
        MyAudioPlayer.get().pausePlayer()
        var currentPlayList = MyPlayService.currentPlayList
        if (currentPlayList.isEmpty()) return
        var playIndex = 0
        for (index in currentPlayList.indices) {
            if (currentPlayList[index].id == mMusicId) {
                playIndex = index - 1
            }
        }
        if (playIndex < 0) {
            playIndex = currentPlayList.size - 1
        }
        changeMusicById(currentPlayList[playIndex].id)

        if (playerDirectoryFragment != null) {
            playerDirectoryFragment.setCurrentPosition(playIndex)
        }
    }

    fun nextPlay(v: View) {
        // mMusicId
        MyAudioPlayer.get().pausePlayer()
        var currentPlayList = MyPlayService.currentPlayList
        if (currentPlayList.isEmpty()) return
        var playIndex = 0
        for (index in currentPlayList.indices) {
            if (currentPlayList[index].id == mMusicId) {
                playIndex = index + 1
            }
        }
        if (playIndex >= currentPlayList.size) {
            playIndex = 0
        }
        changeMusicById(currentPlayList[playIndex].id)

        if (playerDirectoryFragment != null) {
            playerDirectoryFragment.setCurrentPosition(playIndex)
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            play -> {
                autoPlayHandler.removeMessages(1)
                if (!tryEnd!!) {
                    play()
                } else {
                    ToastUtils.showToast("试听结束,请购买")
//                    pay()
                }
            }

            tv_right_title -> {
                // 标题取作品名称，说明取作品详情，图片取作品封面图
                // ToastUtils.showToast("分享的分享到哪里还不确定")
                // mMusicEntity.image
                // mMusicEntity.title
                ShareDialog(mContext, object : ShareDialog.OnClickListener {
                    override fun onCircle() {
                        if (mMusicEntity != null) {
                            WechatUtil.getInstance().shareWechatComment(mContext, "wx35481040e7c07c5f", getString(R.string.app_name), mMusicEntity?.title, "http://api.itunyin.com/api/html/share/id/${mMusicEntity?.id}")
                        }
                    }

                    override fun onWechat() {
                        if (mMusicEntity != null) {
                            WechatUtil.getInstance().shareWechat(mContext, "wx35481040e7c07c5f", getString(R.string.app_name), mMusicEntity?.title, "http://api.itunyin.com/api/html/share/id/${mMusicEntity?.id}", "")
                        }
                    }

                }).show()
            }
            tv_to_pay -> {
//                ToastUtils.showToast("去购买待定")
                pay("2")//单首歌

            }
            tv_to_pay_try -> {//全集
                pay("1")
            }

            iv_collect -> {
                if (isCollected) {
                    // 取消收藏
                    mMusicId?.let { mPresenter.cancelCollect(it) }
                } else {
                    mMusicId?.let { mPresenter.addCollect(it) }
                }
            }
        }
    }

    private fun ivIsPlaying() {
        if (MyAudioPlayer.get().isPreparing) {
            MyAudioPlayer.get().startPlayer()
        } else {
            if (MyAudioPlayer.get().isPlaying) {
                play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
            } else {
                play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_pause))
            }
        }
    }

    private fun ivIsPlayingTwo() {
        if (MyAudioPlayer.get().isPausing) {
            play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
            if (MyAudioPlayer.get().duration.toString().length <= 7) {
                durationTv.text = formatTime(MyAudioPlayer.get().duration)
            }
        }
    }

    private fun play() {
        ivIsPlaying()
        MyAudioPlayer.get().playPause()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mTabLayout.currentTab = position
        if (position == 2) {
            var isVisibility = ly_price.visibility == View.VISIBLE
            if (adapter != null) {
                var list = adapter?.fragments
                for (i in list!!.indices) {
                    if (list[i] is PlayerCommentFragment) {
                        (list[i] as PlayerCommentFragment).setInputLayoutVisibility(isVisibility)
                    }
                }
            }
        }
    }

    override fun onTabSelect(position: Int) {
        mViewPager.currentItem = position
    }

    override fun onTabReselect(position: Int) {

    }

    override fun onResume() {
        super.onResume()
//        if (musicControl!=null){
//            handler.sendEmptyMessage(UPDATE_PROGRESS)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        musicControl?.clear()
//        unbindService(conn)

    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
//                UPDATE_PROGRESS ->
//                    updateProgress()

            }
        }
    }

    //更新进度条
    private fun updateProgress() {
//        val currenPostion = musicControl!!.getCurrenPostion()
//        progressSb.progress = currenPostion
//        progressSb.max = musicControl!!.duration
//        LogUtils.d("progresswangrongchao", formattime((musicControl!!.currenPostion)) + "")
//        if (!TextUtils.isEmpty(mListeningTime)) {
//            if (mListeningTime == formatSecond(currenPostion)) {
//                progressTv.text = formattime((currenPostion))
//                if (musicControl!!.isPlaying) {
//                    play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
//                    musicControl?.play()
//                    val builder = AlertDialog.Builder(this)
//                    builder.setTitle("提示")
//                    builder.setMessage("试听结束，请购买！")
//                    builder.setPositiveButton("去购买",
//                            DialogInterface.OnClickListener { dialogInterface, i ->
//                                //点击“知道了”的后续业务逻辑
//                                ToastUtils.showToast("去购买待定")
//                            })
//                    builder.setNegativeButton("取消",
//                            DialogInterface.OnClickListener { dialogInterface, i ->
//                            })
//                    val dialog = builder.create()
//                    dialog.show()
//                }
//                tryEnd = true
//
//            } else {
//                if (!tryEnd!!) {
//                    progressTv.text = formattime((currenPostion))
//                }
//            }
//        } else {
//            progressTv.text = formattime((currenPostion))
//        }
//
////        LogUtils.d("progresswangrongcha-------op", cur.toString())
//        durationTv.text = formattime(musicControl!!.duration)
////        LogUtils.d(currenPostion.toString())
//        //使用Handler每500毫秒更新一次进度条
//        handler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 500)
    }


    fun formattime(time: Int): String {
        var min = (time / (1000 * 60)).toString() + ""
        var second = (time % (1000 * 60) / 1000).toString() + ""
        if (min.length < 2) {
            min = "0$min"
        }
        if (second.length < 2) {
            second = "0$second"
        }
        return "$min:$second"
    }

    fun formatSecond(time: Int): String {
        var min = (time / (1000 * 60)).toString() + ""
        var second = (time % (1000 * 60) / 1000).toString() + ""
        if (min.length < 2) {
            min = "0$min"
        }
        if (second.length < 2) {
            second = "0$second"
        }
        return second
    }

    private fun formatTime(time: Long): String {
        return SystemUtils.formatTime("mm:ss", time)
    }

    override fun cerateOrderCallBack(createOrderEntity: CreateOrderEntity) {
        // PaySucDialog.getInstance().show(fragmentManager, "1")
        ToastUtils.showToast("购买成功")
        hideLoading()
        tryEnd = false
        mPresenter.getMusic(mMusicId!!)
    }

    override fun payOrderCallBack(string: String) {

    }


    /**
     * type 1全集 2单首
     */
    fun pay(type: String) {
        var moneyPay: String? = null
        if (TextUtils.equals("1", type)) {
            moneyPay = priceAll

        } else {
            moneyPay = priceSingle
        }

        if (type == "1") {
            mPresenter.createOrder("", "", payAllThemeId!!)
        } else if (type == "2") {
            mPresenter.createOrder(mMusicId!!, "", "")
        }
//        BottomDialog.show(this, moneyPay, object : BottomDialog.OnPayClickistener {
//            override fun onPay(dialog: BaseDialog, money: String) {
////                ToastUtils.showToast(money)
//                dialog.dismiss()
//                showLoading()
//                if (type == "1") {
//                    mPresenter.createOrder("", "", payAllThemeId!!)
//                } else if (type == "2") {
//                    mPresenter.createOrder(mMusicId!!, "", "")
//                }
//
//            }
//
//        }, false)

    }

}