package com.will.habit.base.loading

import android.app.Dialog
import android.content.Context

/**
 * Desc: 创建加载中弹框接口
 * <p>
 * Date: 2020/4/28
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: pengyushan
 */
interface LoadingDialogCreate {

    /**
     * Desc: 创建加载中弹框
     * <p>
     * Author: pengyushan
     * Date: 2020/4/29
     *
     * @param context context
     * @param title 加载文案
     */
    fun createLoadingDialog(context: Context, title: String): Dialog
}

/**
 * Desc: 定制加载中弹框
 * <p>
 * Author: pengyushan
 * Date: 2020/4/29
 */
object LoadingDialogProvider {

    private var loadingDialogCreate: LoadingDialogCreate = DefaultLoadingDialog()

    /**
     * Desc: 设置加载中弹框创建接口
     * <p>
     * Author: pengyushan
     * Date: 2020/4/29
     */
    fun setLoadingDialogCreate(loadingDialogCreate: LoadingDialogCreate) {
        this.loadingDialogCreate = loadingDialogCreate
    }

    /**
     * Desc: 创建加载中弹框
     * <p>
     * Author: pengyushan
     * Date: 2020/4/29
     */
    fun createLoadingDialog(context: Context, title: String) = loadingDialogCreate.createLoadingDialog(context, title)

}