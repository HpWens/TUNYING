package com.tunyin

import android.content.Context
import android.content.pm.PackageManager.NameNotFoundException
import android.text.TextUtils
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import com.tunyin.constants.RequestConstants
import com.tunyin.mvp.model.SelfBean
import com.tunyin.network.helper.ApiException
import com.tunyin.network.helper.ResponseBodyInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.util.concurrent.TimeUnit


/**
 * @author ym
 * created at 2018/8/23 9:31
 * desc: okhttp辅助类
 */
object OkHttpHelper {
    // 连接时长
    val DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000
    // 写入时长
    val DEFAULT_WIRTE_TIMEOUT_MILLS = 20 * 1000
    // 读取时长
    val DEFAULT_READ_TIMEOUOT_MILLS = 20 * 1000
    private val PLATFORM_TUNYIN = "1"

    @Volatile
    private var mOkHttpClient: OkHttpClient

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WIRTE_TIMEOUT_MILLS.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUOT_MILLS.toLong(), TimeUnit.MILLISECONDS)
                // 失败重连
                .retryOnConnectionFailure(true)
                .addInterceptor(getHeaderInterceptor())
                // 打印网络信息
                .addInterceptor(getInterceptor())
                .addInterceptor(LogInterceptor())
                .addInterceptor(HandleErrorInterceptor())//15080311579

                // cookie持久化
//                .addInterceptor(ReadCookiesInterceptor())
//                .addInterceptor(SaveCookiesInterceptor())
                .build()
    }

    /**
     * json数据有问题导致解析出错的拦截器
     */
    class HandleErrorInterceptor : ResponseBodyInterceptor() {
        override fun intercept(response: Response, url: String, body: String): Response {
            var jsonObject: JSONObject? = null
            try {
                jsonObject = JSONObject(body)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (jsonObject != null) {
                if (jsonObject.optInt("code", -1) != 200 && jsonObject.has("desc")) {
                    var desc = jsonObject.getString("desc")
                    throw ApiException(desc)
                }
            }
            return response
        }


    }

    /**
     * 设置Header
     *builder.addHeader(RequestConstants.VERSION_CODE, BuildConfig.VERSION_NAME);
    builder.addHeader(RequestConstants.PLATFORM, PLATFORM_PRETTY);
    builder.addHeader("Content-Type", "application/json");
     * @return
     */
    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            if (TextUtils.isEmpty(SelfBean.instance.token)) {
                val requestBuilder = original.newBuilder()
                        .header(RequestConstants.VERSION_CODE, BuildConfig.VERSION_NAME)
                        .header(RequestConstants.PLATFORM, PLATFORM_TUNYIN)
                        .header("Content-Type", "application/json")
                        .header("token", "cc7f9149282b4c239c6d17fcd4c734fc")
                val request = requestBuilder.build()
                chain.proceed(request)
            } else {
                if (chain.request().url().toString().contains("api/file/fileUpload")) {
                    val requestBuilder = original.newBuilder()
                            .header(RequestConstants.VERSION_CODE, BuildConfig.VERSION_NAME)
                            .header(RequestConstants.PLATFORM, PLATFORM_TUNYIN)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                } else {
                    val requestBuilder = original.newBuilder()
                            .header(RequestConstants.VERSION_CODE, BuildConfig.VERSION_NAME)
                            .header(RequestConstants.PLATFORM, PLATFORM_TUNYIN)
                            .header("Content-Type", "application/json")

                            .header("token", SelfBean.instance.token)
                    val request = requestBuilder.build()
                    chain.proceed(request)


                }


            }


        }

    }

    private fun getInterceptor(): Interceptor {

        val interceptor = HttpLoggingInterceptor()
        //显示日志
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    fun getOkHttpClient(): OkHttpClient = mOkHttpClient


    // 读取Cookie的拦截器
//    class ReadCookiesInterceptor : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val builder = chain.request().newBuilder()
//            val cookies = App.instance.getCookies()
//            for (cookie in cookies) {
//                builder.addHeader("Cookie", cookie)
//            }
//            return chain.proceed(builder.build())
//        }
//    }

    // 存储Cookie的拦截器
//    class SaveCookiesInterceptor : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val originalResponse = chain.proceed(chain.request())
//            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//                val cookies = HashSet<String>()
//                for (header in originalResponse.headers("Set-Cookie")) {
//                    cookies.add(header)
//                }
//                App.instance.setCookies(cookies)
//            }
//            return originalResponse
//        }
//    }
}
