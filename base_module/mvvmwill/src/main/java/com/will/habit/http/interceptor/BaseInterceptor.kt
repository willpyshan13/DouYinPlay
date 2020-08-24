package com.will.habit.http.interceptor

import com.will.habit.constant.ConstantConfig
import com.will.habit.utils.SPUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author will
 */
class BaseInterceptor(private val headers: Map<String, String>?) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
                .newBuilder()
        if (headers != null && headers.isNotEmpty()) {
            val keys = headers.keys
            for (headerKey in keys) {
                headers[headerKey]?.let { builder.addHeader(headerKey, it).build() }
            }
        }
        val token = SPUtils.instance.getString(ConstantConfig.TOKEN)
        if (token!=null) {
            builder.addHeader("token", token)
        }
        builder.addHeader("Platform","1")
        //请求信息
        val response= chain.proceed(builder.build())

        return response
    }

}