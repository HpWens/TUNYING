package com.tunyin

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.google.gson.Gson
import com.tunyin.constants.RequestConstants
import com.tunyin.mvp.model.SelfBean
import com.tunyin.myservice.PlayService
import com.tunyin.myservice.db.DBManager
import com.tunyin.utils.AppContext
import com.tunyin.utils.AppUtils
import com.vondear.rxtool.RxTool
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.cache.converter.SerializableDiskConverter
import com.zhouyou.http.model.HttpHeaders
import com.zhouyou.http.model.HttpParams
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/7
 * desc: App主入口
 * * #                                                   #
 * * #                       _oo0oo_                     #
 * * #                      o8888888o                    #
 * * #                      88" . "88                    #
 * * #                      (| -_- |)                    #
 * * #                      0\  =  /0                    #
 * * #                    ___/`---'\___                  #
 * * #                  .' \\|     |# '.                 #
 * * #                 / \\|||  :  |||# \                #
 * * #                / _||||| -:- |||||- \              #
 * * #               |   | \\\  -  #/ |   |              #
 * * #               | \_|  ''\---/''  |_/ |             #
 * * #               \  .-\__  '-'  ___/-. /             #
 * * #             ___'. .'  /--.--\  `. .'___           #
 * * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * * #                       `=---='                     #
 * * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * * #                                                   #
 * * #               佛祖保佑         永无BUG             #
 * * #                                                   #
 */

class App : Application() {
    private var allActivities: HashSet<Activity>? = null
    val mApiComponent: ApiComponent by lazy {
        DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .build()
    }
//    private val mSp = getSharedPreferences(Constants.EXTRA_COOKIE, Context.MODE_PRIVATE)

    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
        AppContext.getInstance().init(applicationContext)
        instance = this
        initNetwork()
        initCrashHandler()
        initLog()
        DBManager.get().init(this)
        val intent = Intent(this, PlayService::class.java)
        startService(intent)

        RxTool.init(this)
        // 初始化
        initEasyHttp()
    }

    companion object {
        lateinit var instance: App
    }

    private fun initEasyHttp() { // 初始化网络框架
        EasyHttp.init(this)
        // 设置请求头
        val headers = HttpHeaders()

        // 设置请求参数
        val params = HttpParams()
        EasyHttp.getInstance()
                .debug("RxEasyHttp", BuildConfig.DEBUG)
                .setReadTimeOut(10 * 60 * 1000.toLong())
                .setWriteTimeOut(10 * 60 * 1000.toLong())
                .setConnectTimeout(10 * 60 * 1000.toLong())
                .setRetryCount(3) // 默认网络不好自动重试3次
                .setRetryDelay(500) // 每次延时500ms重试
                .setRetryIncreaseDelay(500) // 每次延时叠加500ms
                .setBaseUrl(ApiConstants.APP_BASE_URL)
                .setCacheDiskConverter(SerializableDiskConverter()) // 默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024.toLong()) // 设置缓存大小为50M
                .setCacheVersion(1) // 缓存版本为1
                .setCertificates() // 信任所有证书
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCommonHeaders(headers) // 设置全局公共头
                .addCommonParams(params) // 设置全局公共参数
    }

    fun addEasyTokenHeader() {
        val headers = HttpHeaders()
        headers.put("token", SelfBean.instance.token)
        headers.put(RequestConstants.VERSION_CODE, BuildConfig.VERSION_NAME)
        headers.put("Content-Type", "application/json")
        headers.put(RequestConstants.PLATFORM, "1")
        EasyHttp.getInstance().commonHeaders.clear()
        EasyHttp.getInstance().addCommonHeaders(headers)
    }


//    fun getCookies(): HashSet<String> =
//            mSp.getStringSet(Constants.EXTRA_COOKIE, HashSet<String>()) as HashSet<String>
//
//
//    fun setCookies(cookies: java.util.HashSet<String>) =
//            mSp.edit().putStringSet(Constants.EXTRA_COOKIE, cookies).apply()

    private fun initNetwork() = NetworkUtils.startNetService(this)

    private fun initCrashHandler() = CrashHandler.init(this)

    private fun initLog() = LogUtils.init(this)


    /**
     * 增加Activity
     * @param act act
     */
    fun addActivity(act: Activity) {
        if (allActivities == null) {
            allActivities = HashSet()
        } else {
            allActivities?.add(act)
        }
    }

    /**
     * 移除Activity
     * @param act act
     */
    fun removeActivity(act: Activity) {
        allActivities?.remove(act)
    }

    @Synchronized
    fun exitApp() {
        allActivities?.let {
            it.forEach { activity ->
                activity.finish()
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}