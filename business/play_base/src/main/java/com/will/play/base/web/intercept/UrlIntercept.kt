package com.will.play.base.web.intercept

/**
 * Desc: 拦截webView shouldOverrideUrlLoading
 * <p>
 * Date: 2020/01/14
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: pengyushan
 */
interface UrlIntercept {

    fun intercept(url: String): Boolean
}