package com.will.play.aop.login.core

import android.content.Context

/**
 * Desc:登录接口回调
 *
 * @Author: will
 */
interface ILogin {
    /**
     * 登录事件接收
     *
     * @param applicationContext 上下文对象
     * @param userDefine         默认登录状态
     */
    fun login(applicationContext: Context?, userDefine: Int)

    /**
     * 判断是否登录
     *
     * @param applicationContext 上下文对象
     * @return 是否登录
     */
    fun isLogin(applicationContext: Context?): Boolean

    /**
     * 清除登录状态
     *
     * @param applicationContext 上下文对象
     */
    fun clearLoginStatus(applicationContext: Context?)
}