package com.tunyin.ui.fragment.index

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.LogUtils
import com.tunyin.MyAudioPlayer
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseRefreshFragment
import com.tunyin.mvp.contract.index.IndexContract
import com.tunyin.mvp.model.Event
import com.tunyin.mvp.model.IsTryEntity
import com.tunyin.mvp.model.MessageEvent
import com.tunyin.mvp.model.SelfBean
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.mvp.presenter.index.IndexPresenter
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.activity.index.SearchActivity
import com.tunyin.ui.activity.mine.LoginActivity
import com.tunyin.ui.adapter.discovery.DiscoveryRVAdapter
import com.tunyin.ui.adapter.index.*
import com.tunyin.utils.ImageUtil
import kotlinx.android.synthetic.main.fragment_index.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * 首页
 */
class IndexFragment : BaseRefreshFragment<IndexPresenter, IndexEntity>(), IndexContract.View, View.OnClickListener {


    private val mBannerList = ArrayList<IndexEntity.BannerListBean>() // 顶部
    private val mClassifyList = ArrayList<IndexEntity>()
    private val mGuessLikeList = ArrayList<IndexEntity.GuessLikeBean>() // 猜你喜欢
    private val mSleepTopList = ArrayList<IndexEntity.SleepTopBean.ListBeanXXXX>() //最佳哄睡
    private val mAnchorList = ArrayList<IndexEntity>() //热门主播
    private val mFreeList = ArrayList<IndexEntity.FreeListBean>() // 免费版
    private val mFeaturedActivityList = ArrayList<IndexEntity.FeaturedActivityBean>() // 精选活动
    private val mFeaturedRecommendList = ArrayList<IndexEntity.FeaturedRecommendBean.ListBean>() // 精选推荐
    private var mDiscoveryRVAdapter: DiscoveryRVAdapter? = null

    private var isTry: Boolean = false


    override fun initPresenter() = mPresenter.attachView(this)

    override fun initInject() = fragmentComponent.inject(this)

    override fun lazyLoadData() = mPresenter.getIndex()


    override fun getLayoutId(): Int = R.layout.fragment_index

    companion object {
        fun newInstance(): IndexFragment {
            return IndexFragment()
        }
    }

    override fun initWidget() {
        search_layout.setOnClickListener(this)
        et_search_content.setOnClickListener(this)
        tv_expand.setOnClickListener(this)
        tv_play.setOnClickListener(this)
        play.setOnClickListener(this)

//        play.startRotate()


    }

    override fun onClick(p0: View?) {
        when (p0) {
            search_layout, et_search_content -> {
                val intent = Intent(activity, SearchActivity::class.java)
                activity?.startActivity(intent)
            }
            tv_expand -> {
                EventBus.getDefault().post(MessageEvent("Hello EventBus!"))
            }

            tv_play -> {
                startActivity(PlayerActivity.newInstance(context!!, SelfBean.instance.musicHisId))
            }
            play -> {
                if (!isTry) {
                    MyAudioPlayer.get().playPause()
                } else {
                    ToastUtils.showToast("你未购买当前歌曲，请前往购买")
                    startActivity(PlayerActivity.newInstance(context!!, SelfBean.instance.musicHisId))
                }

//                if (isPlay){
//                    play.setBackgroundResource(R.mipmap.icon_stop)
//                    isPlay=!isPlay
//                    play.stopRotate()
//                }else{
//                    play.setBackgroundResource(R.mipmap.icon_pause)
//                    isPlay=!isPlay
//                    play.startRotate()
//                }
//                val event = Event(2,2)
//                EventBusUtil.sendEvent(event)
//                var intent = Intent(mContext, MusicService::class.java)
//                intent.putExtra("musicId", SelfBean.instance.musicUrl)
//                mContext?.startService(intent)
            }


        }
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventBusCome(event: Event<*>?) {
        if (event != null) {
            receiveEvent(event!!)
        }

    }

    var temp: Int = -1
    var isPlay: Boolean = false
    var isShowImg: Boolean = true

    override fun receiveEvent(event: Event<*>) {
        if (event.code == 1) {
            isTry = (event.data as IsTryEntity).isTry
            ImageUtil.load((event.data as IsTryEntity).imageUrl).isCircle.on(play)
            if ((event.data as IsTryEntity).isPlaying) {
                isPlay = true
                play.setBackgroundResource(R.mipmap.icon_pause)//

//                play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_pause))
                play.startRotate()
            } else {
                isPlay = false
                play.setBackgroundResource(R.mipmap.icon_stop)//

//                play.setBackgroundResource(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
                play.stopRotate()
            }
        }


//        if (event.code == 1) {
//            LogUtils.d("----mymusic---------temp=----" + temp + "-------data=" + event.data)
//            if (event.data != temp) {
//                LogUtils.d("-----mymusic----" + event.data)
//                isPlay = true
//                play.setBackgroundResource(R.mipmap.icon_pause)
////                play.setImageDrawable(mContext!!.resources.getDrawable(R.mipmap.icon_pause))
//                play.startRotate()
//            } else {
//
//                LogUtils.d("-----mymusic----" + "暂停")
//                isPlay = false
//                play.setBackgroundResource(R.mipmap.icon_stop)
////                play.setBackgroundResource(mContext!!.resources.getDrawable(R.mipmap.icon_stop))
//                play.stopRotate()
//            }


//            temp = event.data as Int


//        }
    }


    override fun initRecyclerView() {
        mDiscoveryRVAdapter = DiscoveryRVAdapter()
        val mLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mDiscoveryRVAdapter
    }


    override fun showIndex(indexEntity: IndexEntity) {
        LogUtils.d("hellll", "-----index---")
        mBannerList.addAll(indexEntity.bannerList)
        mGuessLikeList.add(indexEntity.guessLike)
        mSleepTopList.addAll(indexEntity.sleepTop.list)
        mAnchorList.add(indexEntity)
        mFreeList.add(indexEntity.freeList)
        mFeaturedActivityList.add(indexEntity.featuredActivity)
        mFeaturedRecommendList.addAll(indexEntity.featuredRecommend.list)
        mClassifyList.add(indexEntity)
        et_search_content.hint = indexEntity.hotSearch
        finishTask()
    }

    override fun showRefreshGuessLike(guessLikeBean: IndexEntity.GuessLikeBean) {
        hideLoading()
        mGuessLikeList.clear()
        mGuessLikeList.add(guessLikeBean)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }

    override fun showRefreshSleepTop(sleepTopBean: IndexEntity.SleepTopBean) {
        hideLoading()
        mSleepTopList.clear()
        mSleepTopList.addAll(sleepTopBean.list)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }

    override fun showRefreshHotAnchor(anchorListBean: List<IndexEntity.AnchorListBean>) {

        mAnchorList.clear()
        var indexEntity = IndexEntity()
        indexEntity?.anchorList.addAll(anchorListBean)
        mAnchorList.add(indexEntity)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }

    override fun showRefreshFree(freeListBean: IndexEntity.FreeListBean) {
        hideLoading()
        mFreeList.clear()
        mFreeList.add(freeListBean)
        mDiscoveryRVAdapter?.notifyDataSetChanged()

    }

    override fun showRreshFeaturedRecommend(featuredRecommendBean: IndexEntity.FeaturedRecommendBean) {
        hideLoading()
        mFeaturedRecommendList.clear()
        mFeaturedRecommendList.addAll(featuredRecommendBean.list)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }


    override fun showError(msg: String) {
        super<IndexContract.View>.showError(msg)
        if (msg != null && (msg.contains("token") || msg.contains("Token"))) {
            SelfBean.instance.token = ""
            var intent = LoginActivity.newInstance(activity)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            return
        }

        hideLoading()
    }

    override fun clear() {
        mBannerList.clear()
        mGuessLikeList.clear()
        mSleepTopList.clear()
        mAnchorList.clear()
        mFreeList.clear()
        mFeaturedActivityList.clear()
        mFeaturedRecommendList.clear()
        mClassifyList.clear()
        mDiscoveryRVAdapter?.removeAllSections()
    }

    var str: String? = ""
    override fun finishTask() {
        if (mBannerList.size != 0) mDiscoveryRVAdapter?.addSection(BannerSelection(mBannerList))
        if (mClassifyList.size != 0) mDiscoveryRVAdapter?.addSection(IndexClassifySelection(mClassifyList))

        var guessLikeSelection = GuessLikeSelection(mGuessLikeList)
        guessLikeSelection.setRefreshGuessLikeListener(object : GuessLikeSelection.OnRefreshGuessLikeListener {
            override fun refreshGuessLikeData() {
                showLoading()
                mPresenter.refreshGuessLike()
            }

        })
        if (mGuessLikeList.size != 0) mDiscoveryRVAdapter?.addSection(guessLikeSelection)


        var sleepTopSelection = SleepTopSelection(mSleepTopList)
        sleepTopSelection.setRefreshSleepTopListener(object : SleepTopSelection.OnRefreshSleepTopListener {
            override fun refreshSleepTopData() {
                showLoading()
                mPresenter.refreshSleepTop()
            }

        })
        if (mSleepTopList.size != 0) mDiscoveryRVAdapter?.addSection(sleepTopSelection)


        var anchorTopSelection = AnchorTopSelection(mAnchorList)
        anchorTopSelection.setRefreshAnchorTopListener(object : AnchorTopSelection.OnRefreshAnchorTopListener {
            override fun refreshAnchorTopData() {
                mPresenter.refreshHotAnchor()
            }

        })
       // if (mAnchorList.size != 0) mDiscoveryRVAdapter?.addSection(anchorTopSelection)

        var freeSelection = FreeSelection(mFreeList)
        freeSelection.setRefreshFreeListener(object : FreeSelection.OnRefreshFreeListener {
            override fun refreshFreeData() {
                showLoading()
                mPresenter.refreshFree()
            }

        })
        if (mFreeList.size != 0) mDiscoveryRVAdapter?.addSection(freeSelection)

//        if (mFreeList.size != 0) {
//            mDiscoveryRVAdapter?.addSection(freeSelection)
//            str = mDiscoveryRVAdapter?.addSection(freeSelection)
//        }

//        freeSelection.setRefreshFreeListener(object : FreeSelection.OnRefreshFreeListener {
//            override fun refreshFreeData() {
//                ToastUtils.showToast("我是换一批")
//                mPresenter.refreshFree("3")
////                mDiscoveryRVAdapter?.removeSection(str)
////                mDiscoveryRVAdapter?.notifyDataSetChanged()
//            }
//
//        })


        // if (mFeaturedActivityList.size != 0) mDiscoveryRVAdapter?.addSection(FeaturedActivitySelection(mFeaturedActivityList))

        var featuredRecommendSelection = FeaturedRecommendSelection(mFeaturedRecommendList)
        featuredRecommendSelection.setRefreshReaturedREcommendListener(object : FeaturedRecommendSelection.OnRefreshReaturedREcommendListener {
            override fun refreshReaturedREcommendData() {
                showLoading()
                mPresenter.refreshFeaturedRecommend()
            }

        })
        if (mFeaturedRecommendList.size != 0) mDiscoveryRVAdapter?.addSection(featuredRecommendSelection)
        mDiscoveryRVAdapter?.notifyDataSetChanged()
    }


//    override fun showRefreshFreeData(freeListBean: IndexEntity.FreeListBean) {
////        mFreeList.clear()
//        mFreeList.add(freeListBean)
////        mDiscoveryRVAdapter?.removeSection(str)
//        mDiscoveryRVAdapter?.notifyDataSetChanged()
//    }


}