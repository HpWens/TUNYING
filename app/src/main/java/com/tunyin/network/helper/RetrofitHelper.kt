package com.tunyin

import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.*
import com.tunyin.mvp.model.mine.*
import com.weike.education.mvp.model.app.SelectionBean
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/9/12
 * desc: RetrofitHelper
 */
class RetrofitHelper(private val mApiService: ApiService) {

    /*******************************ApiService *********************************/

    // 首页层次（学习阶段分层）
//    fun getDiscoveryComment(): Flowable<DiscoveryCommentBean> = mApiService.getDiscoveryCommentData()
//
//    // 获取相应Tag页面的内容
//    fun getVertical(tag: Int): Flowable<VerticalBean> = mApiService.getVertical(tag)
//
//    // 提交精选内容相应区别参数
//    fun setTag(tagList: ArrayList<Int>): Flowable<TagSuccessBean> = mApiService.setTag(tagList)

    // 获取精选内容
    fun getSelection(): Flowable<SelectionBean> = mApiService.getSelectionData()

    //获取发现
    fun getDisCovery(): Flowable<BaseEntity<DiscoveryEntity>> = mApiService.getDiscoveryData()

    //获取首页
    fun getIndex(): Flowable<BaseEntity<IndexEntity>> = mApiService.getIndexData()

    //首页猜你喜欢换一批
    fun refreshGuessLike(): Flowable<BaseEntity<IndexEntity.GuessLikeBean>> = mApiService.refreshGuessLike()

    //首页最佳哄睡榜换一批
    fun refreshSleepTop(): Flowable<BaseEntity<IndexEntity.SleepTopBean>> = mApiService.refreshSleepTop()

    //首页热门主播换一批
    fun refreshHotAnchor(): Flowable<BaseEntity<IndexEntity.AnchorListBean>> = mApiService.refreshHotAnchor()

    //首页免费版换一批
    fun refreshFree(): Flowable<BaseEntity<IndexEntity.FreeListBean>> = mApiService.refreshFree()

    //首页精选推荐换一批
    fun refreshFeaturedRecommend(): Flowable<BaseEntity<IndexEntity.FeaturedRecommendBean>> = mApiService.refreshFeaturedRecommend()


    fun login(phone: String, password: String): Flowable<BaseEntity<LoginEntity>> = mApiService.login(phone, password)

    //获取音乐
    fun getMusic(songId: String): Flowable<BaseEntity<MusicEntity>> = mApiService.getMusic(songId)


    fun getMusicDetailSingle(songId: String): Flowable<BaseEntity<PalyDetailSingleEntity>> = mApiService.getMusicDetailSingle(songId)

    fun tunyinVip(): Flowable<BaseEntity<TunyinVipEntity>> = mApiService.tunyinVip()


    fun payTunyinVip(nobleEquityId: String,type: String): Flowable<BaseEntity<String>> = mApiService.payTunyinVip(nobleEquityId, type)

    //换一批
    fun refreshFree(noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.BroadcastBean>> = mApiService.refreshFree(noteClassId)

    //换一批
    fun refreshDisPaid(noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.PaidSelectionBean>> = mApiService.refreshDisPaid(noteClassId)

    //换一批
    fun refreshDisThemeSleep(noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.ThemeSleepBean>> = mApiService.refreshDisThemeSleep(noteClassId)


    //文件上传
    fun fileUpload(part: MultipartBody.Part): Flowable<BaseEntity<UploadFileEntity>> = mApiService.fileUpload(part)

    //换一批
    fun refreshDisRadio(noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.RadioBean>> = mApiService.refreshDisRadio(noteClassId)

    //我的钱包
    fun myWallet(): Flowable<BaseEntity<MyWalletEntity>> = mApiService.myWallet()


    //支付信息
    fun getPayInfo( map: Map<String, String>): Flowable<BaseEntity<PayInfoEntity>> = mApiService.getPayInfo(map)


    //播放页面歌曲目录
    fun getPlayerDirectory(songId: String): Flowable<BaseEntity<PlayerDirectoryEntity>> = mApiService.getPlayerDirectory(songId)

    //播放页面歌曲评论
    fun getPlayerComment(songId: String): Flowable<BaseEntity<PlayerCommentEntity>> = mApiService.getPlayerComment(songId)


    //点赞歌曲评论
    fun addPraise(commentId: String): Flowable<BaseEntity<String>> = mApiService.addPraise(commentId)


    //点赞歌曲评论
    fun cancelPraise(commentId: String): Flowable<BaseEntity<String>> = mApiService.canclePraise(commentId)


    //发表评论
    fun addCommend(songId:String,content:String): Flowable<BaseEntity<AddCommendEntity>> = mApiService.addCommend(songId, content)

    //搜索
    fun search(offset: String, limit: String, name: String, classifyId: String): Flowable<BaseEntity<SearchEntity>> = mApiService.search(offset, limit, name, classifyId)

    //收藏
    fun myCollect(offset: String, limit: String): Flowable<BaseEntity<CollectEntity>> = mApiService.myCollect(offset, limit)


    //优惠券
    fun myVoucher(offset: String, limit: String, type: String): Flowable<BaseEntity<VoucherEntity>> = mApiService.myVoucher(offset, limit, type)


    //订单列表
    fun getOrderList(offset: String, limit: String): Flowable<BaseEntity<OrderEntity>> = mApiService.getOrderList(offset, limit)


    //删除订单
    fun getOrderList(orderId: String): Flowable<BaseEntity<String>> = mApiService.delOrder(orderId)

    //搜索热门
    fun searchHot(): Flowable<BaseEntity<SearchHotEntity>> = mApiService.searchHot()

    //搜索历史
    fun searchHistory(): Flowable<BaseEntity<SearchHistoryEntity>> = mApiService.searchHistory()

    //删除搜索
    fun deleteSearch(id: String): Flowable<BaseEntity<String>> = mApiService.deleteSearch(id)

    //获取验证码
    fun sendMsm(phone: String, type: String): Flowable<BaseEntity<String>> = mApiService.sendMsm(phone, type)


    //注册
    fun register(phone: String, password: String, nickName: String, code: String): Flowable<BaseEntity<RegisterEntity>> = mApiService.register(phone, password, nickName, code)

    //付费精选
    fun paidSelection(offset: String, limit: String): Flowable<BaseEntity<PayStuffEntity>> = mApiService.paidSelection(offset, limit)

    //付费精选banner
    fun paidSelectionBanner(): Flowable<BaseEntity<PayStaffBannerEntity>> = mApiService.paidSelectionBanner()


    //忘记密码
    fun forgetPwd(phone: String, password: String, code: String): Flowable<BaseEntity<String>> = mApiService.forgetPwd(phone, password, code)

    //排行榜
    fun rankList(offset: String, limit: String, type: String): Flowable<BaseEntity<RankListEntity>> = mApiService.rankList(offset, limit, type)

    fun createOrder(songDetails: String, couponId: String, themeId:String): Flowable<BaseEntity<CreateOrderEntity>> = mApiService.createOrder(songDetails, couponId,themeId)

    fun payOrder(orderId: String): Flowable<BaseEntity<String>> = mApiService.payOrder(orderId)


    // 获取头像内容,名字
//    fun getMine(): Flowable<MineBean> = mApiService.getMineData()
//
//    // 获取视频流
//    fun getVideo(): Flowable<VideoBean> = mApiService.getVideoData()
}

