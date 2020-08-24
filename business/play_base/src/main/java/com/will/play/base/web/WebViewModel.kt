package com.will.play.base.web

import android.app.Application
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.webkit.*
import androidx.annotation.RequiresApi
import com.will.habit.base.BaseModel
import com.will.habit.base.BaseViewModel
import com.will.habit.bus.event.SingleLiveEvent
import com.will.play.base.web.intercept.CloseIntercept
import com.will.play.base.web.intercept.EmailIntercept
import org.xml.sax.helpers.DefaultHandler
import java.net.HttpURLConnection

/**
 * Desc: 通用浏览器
 * <p>
 * Date: 2019/12/2
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time: 2019/12/2
 * Update Comments:
 *
 * @author: pengyushan
 */
class WebViewModel(val context: Application,
                   var url: String?,
                   val title: String?)
    : BaseViewModel<BaseModel<Any>>(context) {

    private val urlIntercepts = mutableListOf(EmailIntercept(), CloseIntercept(this))
    /**
     * 选择图片回调
     */
    var filePathCallback: ValueCallback<Array<Uri>>? = null
    /**
     * 选择图片
     */
    val photoSelect = SingleLiveEvent<Unit>()

    override fun onCreate() {
        super.onCreate()
        setTitleText(title.orEmpty())
    }

    var showErrorView = false

    /**
     * JsBrdige 默认Handler
     */
    val defaultHandler = DefaultHandler()

    val webChromeClient = object : WebChromeClient() {

        override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: FileChooserParams): Boolean {
            this@WebViewModel.filePathCallback = filePathCallback
            photoSelect.call()
            return true
        }
    }

    val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (url.isNullOrEmpty()) {
                return true
            }
            if (URLUtil.isNetworkUrl(url)) {
                view?.loadUrl(url)
            } else {
                urlIntercepts.firstOrNull { it.intercept(url) }
            }
            return true
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            if (url == request?.url.toString()) {
                showErrorView = true
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (!showErrorView) {
            }
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            // 页面SSL证书存在问题, 不要使用handler.proceed()忽略该问题, 否则无法上架google商店，应该让H5同学确认修正
            super.onReceivedSslError(view, handler, error)
            showErrorView = true
        }

        override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
            super.onReceivedHttpError(view, request, errorResponse)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && errorResponse?.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                showErrorView = true
            }
        }
    }

    companion object {

        /**
         * 语言环境
         */
        private const val LANG_KEY = "lang"
    }
}
