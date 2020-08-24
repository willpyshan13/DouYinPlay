package com.will.play.base.web;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.BindingAdapter;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

/**
 *
 * @author goldze
 * @date 2017/6/18
 */
public class ViewAdapter {

    /**
     * Desc:JsBrdige 加载Html数据
     * <p>
     * Date: 2019-04-08
     *
     * @param webView
     * @param html
     */
    @BindingAdapter({"loadJsBridgeHtml"})
    public static void loadJsBridgeHtml(BridgeWebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadUrl(html);
        }
    }

    /**
     * Desc:JsBrdige 加载DefaultHandler
     * Date: 2019-04-08
     *
     * @param webView
     * @param defaultHandler
     */
    @BindingAdapter({"loadJsDefaultHandle"})
    public static void loadJsDefaultHandle(BridgeWebView webView, final DefaultHandler defaultHandler) {
        if (defaultHandler != null) {
            webView.setDefaultHandler(defaultHandler);
        }
    }

    /**
     * Desc:设置JS Client监听代理
     *
     * @param webView
     * @param webChromeClient
     */
    @BindingAdapter({"setWebChromeClient"})
    public static void setWebChromeClient(BridgeWebView webView, WebChromeClient webChromeClient) {
        webView.setWebChromeClient(webChromeClient);
    }

    /**
     * Desc:设置webview client监听代理
     *
     * @param webView
     * @param webChromeClient
     */
    @BindingAdapter({"setWebViewClient"})
    public static void setWebViewClient(BridgeWebView webView, WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
    }

    /**
     * Desc:JsBrdidge注册回调
     * <p>
     * Date: 2019-04-08
     *
     * @param webView
     * @param handlerName
     * @param handler
     */
    @BindingAdapter({"handlerName", "bridgeHandler"})
    public static void registerHandler(BridgeWebView webView, String handlerName, BridgeHandler handler) {
        webView.registerHandler(handlerName, handler);
    }

    /**
     * Desc:JS交互
     * <p>
     * Date: 2019-04-08
     *
     * @param webView
     * @param sendMessage
     */
    @BindingAdapter({"send"})
    public static void send(BridgeWebView webView, String sendMessage) {
        webView.send(sendMessage);
    }

    /**
     * Desc:JS交互
     * <p>
     * Date: 2019-04-08
     *
     * @param webView
     * @param sendMessage
     * @param responseCallback
     */
    @BindingAdapter(value = {"sendMessage", "callBackFunction"})
    public static void sendCallback(BridgeWebView webView, String sendMessage, CallBackFunction responseCallback) {
        webView.send(sendMessage, responseCallback);
    }

    /**
     * Desc:点击JS页面回调
     * Date: 2019-04-08
     *
     * @param webView
     * @param handlerName 方法名
     * @param handlerData        传递数据
     * @param callBackFunction    点击回调
     */
    @BindingAdapter({"handlerName", "handlerData", "callBackFunction"})
    public static void onClickHandler(BridgeWebView webView, String handlerName, String handlerData, CallBackFunction callBackFunction) {
        webView.callHandler(handlerName, handlerData, callBackFunction);
    }
}
