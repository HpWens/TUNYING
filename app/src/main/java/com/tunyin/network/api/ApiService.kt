package com.tunyin

import com.tunyin.mvp.model.BaseEntity
import com.tunyin.mvp.model.CancelCollectEntity
import com.tunyin.mvp.model.UploadFileEntity
import com.tunyin.mvp.model.UserBean
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.mvp.model.index.*
import com.tunyin.mvp.model.mine.*
import com.weike.education.mvp.model.app.SelectionBean
import io.reactivex.Flowable
import okhttp3.MultipartBody
import retrofit2.http.*

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/9/12
 * desc: Api接口类
 */
interface ApiService {

    /**
    //     * 首页层次（学习阶段分层）
    //     */
//    @GET("/course3/api/content/stages?api_ver=1.10&keyfrom=course.3.2.2.android&model=MI_6&mid=8.0.0&imei=866822031582307&vendor=xiaomi&screen=1080x1920&abtest=6&Mkt1st=xiaomi&Mkt=xiaomi&Pdt=mCourse.android")
//    fun getDiscoveryCommentData(): Flowable<DiscoveryCommentBean>
//
//    /**
//     * 获取相应Tag页面的内容(tag由上面接口获取)
//     */
//
//    @GET("/course3/api/vertical2?api_ver=1.10&keyfrom=course.3.2.2.android&model=MI_6&mid=8.0.0&imei=866822031582307&vendor=xiaomi&screen=1080x1920&abtest=6&Mkt1st=xiaomi&Mkt=xiaomi&Pdt=mCourse.android")
//    fun getVertical(@Query("tag") tag: Int): Flowable<VerticalBean>
//
//    /**
//     * 修改当前学习阶段
//     */
//    @GET("/course3/api/user/profile/tag/set?api_ver=1.10&keyfrom=course.3.2.2.android&model=MI_6&mid=8.0.0&imei=866822031582307&vendor=xiaomi&screen=1080x1920&abtest=6&Mkt1st=xiaomi&Mkt=xiaomi&Pdt=mCourse.android")
//    fun setTag(@Query("tags") tagList: ArrayList<Int>): Flowable<TagSuccessBean>
//
    /**
     * 获取精选内容
     */
    @GET("/course3/api/apphome?api_ver=1.10&keyfrom=course.3.2.2.android&model=MI_6&mid=8.0.0&imei=866822031582307&vendor=xiaomi&screen=1440x2560&abtest=6&Mkt1st=xiaomi&Mkt=xiaomi&Pdt=mCourse.android&popup=false")
    fun getSelectionData(): Flowable<SelectionBean>

    /**
     * 获取发现
     */
    @POST("api/home/getFind")
    fun getDiscoveryData(): Flowable<BaseEntity<DiscoveryEntity>>


    @POST("api/home/getMyGrade")
    fun getMyGrade(): Flowable<BaseEntity<MyGradeEntity>>

    /**
     * 获取首页
     */
    @POST("api/home/getAppHome")
    fun getIndexData(): Flowable<BaseEntity<IndexEntity>>

    /**
     * 首页猜你喜欢换一批
     */
    @POST("api/home/guessLikeChange")
    fun refreshGuessLike(): Flowable<BaseEntity<IndexEntity.GuessLikeBean>>

    /**
     * 首页最佳哄睡换一批
     */
    @POST("api/home/sleepTopChange")
    fun refreshSleepTop(): Flowable<BaseEntity<IndexEntity.SleepTopBean>>

    /**
     * 首页热门主播换一批
     */
    @POST("api/home/hotAnchor")
    fun refreshHotAnchor(): Flowable<BaseEntity<List<IndexEntity.AnchorListBean>>>

    /**
     * 首页免费版换一批
     */
    @POST("api/home/freeListChange")
    fun refreshFree(): Flowable<BaseEntity<IndexEntity.FreeListBean>>


    /**
     * 首页精选推荐换一批
     */
    @POST("api/home/featuredRecommendChange")
    fun refreshFeaturedRecommend(): Flowable<BaseEntity<IndexEntity.FeaturedRecommendBean>>


//
//    /**
//     * 获取头像，名字
//     */
//    @GET("/api/user_status.jsonp?&keyfrom=course.3.2.4.android&model=MI_6&mid=8.0.0&imei=866822031582307&vendor=xiaomi&screen=1080x1920&abtest=6&Mkt1st=xiaomi&Mkt=xiaomi&Pdt=mCourse.android")
//    fun getMineData(): Flowable<MineBean>
//
//    /**
//     * 获取视频流
//     */
//    @GET("/course3/api/content/video?api_ver=2.0&rank=0&keyfrom=course.3.2.4.android&model=MI_6&mid=8.0.0&imei=866822031582307&vendor=xiaomi&screen=1080x1920&abtest=6&Mkt1st=xiaomi&Mkt=xiaomi&Pdt=mCourse.android")
//    fun getVideoData(): Flowable<VideoBean>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("api/user/login")
    fun login(@Field("phone") phone: String,
              @Field("password") password: String): Flowable<BaseEntity<LoginEntity>>

    /**
     * 获取音乐详情
     */
    @FormUrlEncoded
    @POST("api/song/songDetail")
    fun getMusic(@Field("songId") songId: String): Flowable<BaseEntity<MusicEntity>>

    /**
     * 单手歌曲详情
     */
    @FormUrlEncoded
    @POST("api/song/songDetailTab")
    fun getMusicDetailSingle(@Field("songId") songId: String): Flowable<BaseEntity<PalyDetailSingleEntity>>

    /**
     * 豚因vip
     */
    @POST("api/noble/rechargge")
    fun tunyinVip(): Flowable<BaseEntity<TunyinVipEntity>>

    /**
     * vip支付
     */
    @FormUrlEncoded
    @POST("api/song/songDetailTab")
    fun payTunyinVip(@Field("nobleEquityId") nobleEquityId: String,
                     @Field("type") type: String): Flowable<BaseEntity<String>>


    /**
     * 换一批
     */
    @FormUrlEncoded
    @POST("api/home/getFindChange")
    fun refreshFree(@Field("noteClassId") noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.BroadcastBean>>


    /**
     * 换一批
     */
    @FormUrlEncoded
    @POST("api/home/getFindChange")
    fun refreshDisPaid(@Field("noteClassId") noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.PaidSelectionBean>>

    /**
     * 换一批
     */
    @FormUrlEncoded
    @POST("api/home/getFindChange")
    fun refreshDisThemeSleep(@Field("noteClassId") noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.ThemeSleepBean>>

    /**
     * 换一批
     */
    @FormUrlEncoded
    @POST("api/home/getFindChange")
    fun refreshDisRadio(@Field("noteClassId") noteClassId: String): Flowable<BaseEntity<DiscoveryEntity.RadioBean>>


    /**
     * 播放页面歌曲目录
     */
    @FormUrlEncoded
    @POST("api/song/songDetailCatalog")
    fun getPlayerDirectory(@Field("songId") songId: String): Flowable<BaseEntity<PlayerDirectoryEntity>>


    /**
     * 播放页面歌曲评论
     */
    @FormUrlEncoded
    @POST("api/song/songDetailComment")
    fun getPlayerComment(@Field("songId") songId: String): Flowable<BaseEntity<PlayerCommentEntity>>


    /**
     * 点赞歌曲评论
     */
    @FormUrlEncoded
    @POST("api/song/addPraise")
    fun addPraise(@Field("commentId") commentId: String): Flowable<BaseEntity<String>>


    /**
     * 取消点赞歌曲评论
     */
    @FormUrlEncoded
    @POST("api/song/cancelPraise")
    fun canclePraise(@Field("commentId") commentId: String): Flowable<BaseEntity<String>>


    /**
     * 添加评论
     */
    @FormUrlEncoded
    @POST("api/song/addComment")
    fun addCommend(@Field("songId") songId: String,
                   @Field("content") content: String): Flowable<BaseEntity<AddCommendEntity>>

    /**
     * 搜索
     */
    @FormUrlEncoded
    @POST("api/song/search")
    fun search(@Field("offset") offset: String,
               @Field("limit") limit: String,
               @Field("name") name: String,
               @Field("classifyId") classifyId: String): Flowable<BaseEntity<SearchEntity>>


    /**
     * 收藏
     */
    @FormUrlEncoded
    @POST("api/song/collectGetList")
    fun myCollect(@Field("offset") offset: String,
                  @Field("limit") limit: String): Flowable<BaseEntity<CollectEntity>>

    /**
     * 取消收藏
     */
    @FormUrlEncoded
    @POST("api/song/collectCancel")
    fun cancelCollect(@Field("songId") songId: String): Flowable<BaseEntity<CancelCollectEntity>>


    /**
     * 添加收藏
     */
    @FormUrlEncoded
    @POST("/api/song/collectAdd")
    fun addCollect(@Field("songId") songId: String): Flowable<BaseEntity<String>>

    /**
     * 优惠券
     */
    @FormUrlEncoded
    @POST("api/user/myCoupon")
    fun myVoucher(@Field("offset") offset: String,
                  @Field("limit") limit: String,
                  @Field("type") name: String): Flowable<BaseEntity<VoucherEntity>>

    /**
     * 已购列表
     */
    @FormUrlEncoded
    @POST("api/order/getList")
    fun getOrderList(@Field("offset") offset: String,
                     @Field("limit") limit: String): Flowable<BaseEntity<OrderEntity>>


    /**
     * 更新头像
     */
    @FormUrlEncoded
    @POST("api/user/updateData")
    fun postHeader(@Field("headUrl") header: String): Flowable<BaseEntity<String>>


    /**
     * 更新用户信息
     */
    @FormUrlEncoded
    @POST("api/user/updateData")
    fun updateUserInfo(@Field("headUrl") headUrl: String,
                       @Field("nickName") nickName: String,
                       @Field("sex") sex: String,
                       @Field("birthday") birthday: String,
                       @Field("messageNotice") messageNotice: String): Flowable<BaseEntity<String>>

    /**
     * 文件上传
     */
    @Multipart
    @POST("api/file/fileUpload")
    fun fileUpload(@Part part: MultipartBody.Part): Flowable<BaseEntity<UploadFileEntity>>


    /**
     * 意见反馈
     */
    @FormUrlEncoded
    @POST("api/user/opinion")
    fun postFeedback(@Field("content") content: String, @Field("images") imgs: String): Flowable<BaseEntity<String>>

    /**
     * 文件上传
     */
    @POST("api/home/getMyInfo")
    fun getUserInfo(): Flowable<BaseEntity<UserBean>>


    /**
     * 获取版本
     */
    @POST("api/sys/getVersion")
    fun getVersion(): Flowable<BaseEntity<VersionEntity>>

    /**
     * 删除订单
     */
    @FormUrlEncoded
    @POST("api/order/delOrder")
    fun delOrder(@Field("orderId") orderId: String): Flowable<BaseEntity<String>>


    /**
     * 我的钱包
     */
    @POST("api/user/myWallet")
    fun myWallet(): Flowable<BaseEntity<MyWalletEntity>>


    /**
     * 获取支付信息
     */
    @FormUrlEncoded
    @POST("api/order/getPayInfo")
    fun getPayInfo(@FieldMap map: Map<String, String>): Flowable<BaseEntity<PayInfoEntity>>


    /**
     * 热门搜索
     */
    @POST("api/song/hotSearch")
    fun searchHot(): Flowable<BaseEntity<SearchHotEntity>>

    /**
     * 搜索历史
     */
    @POST("api/song/getSearchHistory")
    fun searchHistory(): Flowable<BaseEntity<SearchHistoryEntity>>

    /**
     * 删除搜索
     */
    @FormUrlEncoded
    @POST("api/song/delSearchHistory")
    fun deleteSearch(@Field("classifyId") classifyId: String): Flowable<BaseEntity<String>>

    /**
     * 发送短信
     */
    @FormUrlEncoded
    @POST("api/sys/sendSms")
    fun sendMsm(@Field("phone") phone: String, @Field("type") type: String): Flowable<BaseEntity<String>>


    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("api/user/register")
    fun register(@Field("phone") phone: String,
                 @Field("password") password: String,
                 @Field("nickName") nickName: String,
                 @Field("code") code: String): Flowable<BaseEntity<RegisterEntity>>

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("api/user/forgetPassword")
    fun forgetPwd(@Field("phone") phone: String,
                  @Field("password") password: String,
                  @Field("code") code: String): Flowable<BaseEntity<String>>


    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("api/user/changePassword")
    fun changePassword(@Field("password") password: String,
                       @Field("code") code: String): Flowable<BaseEntity<String>>

    /**
     * 付费精选
     */
    @FormUrlEncoded
    @POST("api/home/paidSelection")
    fun paidSelection(@Field("offset") offset: String,
                      @Field("limit") limit: String): Flowable<BaseEntity<PayStuffEntity>>

    /**
     * 获取电台 广播剧
     */
    @FormUrlEncoded
    @POST("api/song/search")
    fun getHomeBroadcastAndStation(@Field("offset") offset: String,
                                   @Field("limit") limit: String,
                                   @Field("classifyId") classifyId: String): Flowable<BaseEntity<PayStuffEntity>>


    /**
     * 付费精选banner
     */
    @POST("api/home/paidSelectionBanner")
    fun paidSelectionBanner(): Flowable<BaseEntity<PayStaffBannerEntity>>


    /**
     * 排行榜
     */
    @GET("api/song/top")
    fun rankList(@Query("offset") offset: String,
                 @Query("limit") limit: String,
                 @Query("type") type: String): Flowable<BaseEntity<RankListEntity>>


//    @FormUrlEncoded
//    @POST("user/login")
//    abstract fun login(@Field("username") username: String,
//                       @Field("password") password: String): Flowable<BaseObjectBean<LoginBean>>

    /**
     * 创建订单
     */
    @FormUrlEncoded
    @POST("api/order/createOrder")
    fun createOrder(@Field("songDetails") songDetails: String,
                    @Field("couponId") couponId: String,
                    @Field("themeId") themeId: String): Flowable<BaseEntity<CreateOrderEntity>>

    /**
     * 订单支付
     */
    @FormUrlEncoded
    @POST("api/order/payOrder")
    fun payOrder(@Field("orderId") orderId: String): Flowable<BaseEntity<String>>


}
