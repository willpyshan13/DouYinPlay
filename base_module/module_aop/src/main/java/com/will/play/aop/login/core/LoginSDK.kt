package com.will.play.aop.login.core

import android.content.Context

/**
 * Desc:登录SDK
 *
 *
 *
 * @Author: will
 */
class LoginSDK private constructor() {
    fun init(context: Context?, iLogin: ILogin?) {
        LoginAssistant.getInstance().applicationContext = context
        LoginAssistant.getInstance().setiLogin(iLogin)
    }

    /**
     * 单点登录，验证token失效的统一接入入口
     */
    fun serverTokenInvalidation(userDefine: Int) {
        LoginAssistant.getInstance().serverTokenInvalidation(userDefine)
    }

    /**
     *
     *
     * Author: will
     */
    fun servserUpdataToken() {
        LoginAssistant.getInstance().setLoginSuccessStatus()
    }

    /**
     * Author: will
     */
    fun serverFailedToken() {
        LoginAssistant.getInstance().setLoginFailedStatus()
    }

    /**
     * Author: will
     * Date: 2020-01-06
     *
     */
    fun isLogin(context: Context?): Boolean {
        return LoginAssistant.getInstance().getiLogin().isLogin(context)
    }

    companion object {
        var instance: LoginSDK? = null
            get() {
                var tmpLoginSdk = field
                if (tmpLoginSdk == null) {
                    synchronized(LoginSDK::class.java) {
                        tmpLoginSdk = field
                        if (tmpLoginSdk == null) {
                            tmpLoginSdk = LoginSDK()
                            field = tmpLoginSdk
                        }
                    }
                }
                return tmpLoginSdk
            }
    }
}