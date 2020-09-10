package com.will.habit.http

import android.text.TextUtils
import com.will.habit.BuildConfig
import com.will.habit.http.cookie.CookieJarImpl
import com.will.habit.http.cookie.store.PersistentCookieStore
import com.will.habit.http.interceptor.BaseInterceptor
import com.will.habit.http.interceptor.CacheInterceptor
import com.will.habit.http.interceptor.RetryInterceptor
import com.will.habit.http.interceptor.logging.Level
import com.will.habit.http.interceptor.logging.LoggingInterceptor
import com.will.habit.utils.KLog.e
import com.will.habit.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by will
 * RetrofitClient封装单例类, 实现网络请求
 */
class RetrofitClient private constructor(url: String = baseUrl, headers: Map<String, String>? = null) {
    private var cache: Cache? = null
    private var httpCacheDirectory: File? = null

    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the `service` interface.
     */
    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit.create(service)
    }

    companion object {
        //超时时间
        private const val DEFAULT_TIMEOUT = 20

        //缓存时间
        private const val CACHE_TIMEOUT = 10 * 1024 * 1024

        //服务端根路径
        var baseUrl = "http://test.weizhiyx.com/"
        var baseTbkUrl = "http://api.tbk.dingdanxia.com/auth?state=custom_4072_"
        var baseTbkUrlView = "&view=wap"
        private val mContext = Utils.getContext()
        private var okHttpClient: OkHttpClient? = null
        private lateinit var retrofit: Retrofit

        @JvmStatic
        val instance: RetrofitClient
            get() = SingletonHolder.INSTANCE

        /**
         * / **
         * execute your customer API
         * For example:
         * MyApiService service =
         * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
         *
         *
         * RetrofitClient.getInstance(MainActivity.this)
         * .execute(service.lgon("name", "password"), subscriber)
         * * @param subscriber
         */
        fun <T> execute(observable: Observable<T>, subscriber: Observer<T>?): T? {
            observable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber)
            return null
        }
    }

    init {
        var url: String? = url
        if (TextUtils.isEmpty(url)) {
            url = baseUrl
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "will_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory!!, CACHE_TIMEOUT.toLong())
            }
        } catch (e: Exception) {
            e("Could not create http cache", e)
        }
        val sslParams = HttpsUtils.getSslSocketFactory()
        okHttpClient = OkHttpClient.Builder()
                .addInterceptor(RetryInterceptor(0))
                .cookieJar(CookieJarImpl(PersistentCookieStore(mContext))) //                .cache(cache)
                .addInterceptor(BaseInterceptor(headers))
                .addInterceptor(CacheInterceptor(mContext))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(LoggingInterceptor.Builder() //构建者模式
                        .loggable(BuildConfig.DEBUG) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .request("Request") // request的Tag
                        .response("Response") // Response的Tag
                        .addHeader("log-header", "I am the log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                        .build()
                )
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS)) // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build()
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }
}