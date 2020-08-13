package com.will.habit.http.interceptor

import android.text.TextUtils
import com.will.habit.constant.ConstantConfig
import com.will.habit.utils.SPUtils
import com.will.play.aop.login.core.LoginSDK
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

/**
 * Desc:重试拦截器
 *
 * Date: 2020-08-13
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class RetryInterceptor(val maxRetry:Int): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var retryNum = 0
        val request = chain.request()
        var response = chain.proceed(request)
        val authorization = response.header("Authorization")
        val code = response.code
        if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            //未登录或者token失效
            LoginSDK.instance?.serverTokenInvalidation(0)
        } else if (response.isSuccessful && !TextUtils.isEmpty(authorization)) {
            SPUtils.instance.put(ConstantConfig.AUTHORIZATION, authorization)
        }
        while (!response.isSuccessful && retryNum < maxRetry) {
            retryNum++
            response.close()
            response = chain.proceed(request)
        }
        return response
    }
}