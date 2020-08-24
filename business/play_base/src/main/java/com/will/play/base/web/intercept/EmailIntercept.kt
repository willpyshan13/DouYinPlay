package com.will.play.base.web.intercept

import android.content.Intent
import android.net.Uri
import com.will.habit.base.BaseApplication

/**
 * Desc: Email跳转
 * <p>
 * Date: 2020/01/14
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: pengyushan
 */
class EmailIntercept : UrlIntercept {

    companion object {

        private const val EMAIL = "mail"
    }

    override fun intercept(url: String): Boolean {
        if (url.startsWith(EMAIL)) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            BaseApplication.instance.startActivity(intent)
            return true
        }
        return false
    }
}