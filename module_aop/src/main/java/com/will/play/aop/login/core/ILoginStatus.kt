package com.will.play.aop.login.core

/**
 * Desc:登录状态
 *
 * @Author: will
 */
interface ILoginStatus {
    /**
     * 登录成功
     */
    fun loginSuccess()

    /**
     * 登录失败
     */
    fun loginFailed()
}