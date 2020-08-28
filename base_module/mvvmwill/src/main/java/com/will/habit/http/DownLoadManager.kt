package com.will.habit.http

import com.will.habit.http.download.DownLoadSubscriber
import com.will.habit.http.download.ProgressCallBack
import com.will.habit.http.interceptor.ProgressInterceptor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

/**
 * @author will
 */
class DownLoadManager private constructor() {
    //下载
    fun load(downUrl: String?, callBack: ProgressCallBack<*>) {
        retrofit!!.create(ApiService::class.java)
                .download(downUrl)
                .subscribeOn(Schedulers.io()) //请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .doOnNext { responseBody -> callBack.saveFile(responseBody) }
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(DownLoadSubscriber(callBack))
    }

    private fun buildNetWork() {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ProgressInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(NetworkUtil.url)
                .build()
    }

    private interface ApiService {
        @Streaming
        @GET
        fun download(@Url url: String?): Observable<ResponseBody?>
    }

    companion object {
        /**
         * 单例模式
         *
         * @return DownLoadManager
         */
        val instance: DownLoadManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DownLoadManager() }

        private var retrofit: Retrofit? = null

    }

    init {
        buildNetWork()
    }
}