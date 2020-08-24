package com.will.play.base.web.intercept

import com.will.habit.base.BaseViewModel

/**
 * Desc: 关闭页面
 * <p>
 * Date: 2020/02/27
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: pengyushan
 */
class CloseIntercept(private val viewModel: BaseViewModel<*>) : UrlIntercept {

    companion object {

        private const val CLOSE_PAGE = "closepage"
    }

    override fun intercept(url: String): Boolean {
        if (url.startsWith(CLOSE_PAGE)) {
            viewModel.finish()
            return true
        }
        return false
    }
}