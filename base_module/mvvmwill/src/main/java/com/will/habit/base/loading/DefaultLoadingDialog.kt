package com.will.habit.base.loading

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import com.will.habit.widget.dialog.LoadingDialog

/**
 * Desc: 默认加载中弹框
 * <p>
 * Date: 2020/4/28
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: pengyushan
 */
class DefaultLoadingDialog : LoadingDialogCreate {

    override fun createLoadingDialog(context: Context, title: String): Dialog {
        return LoadingDialog.Builder(context)
                .setMessage(title)
                .setShowMessage(!TextUtils.isEmpty(title))
                .create()
    }
}