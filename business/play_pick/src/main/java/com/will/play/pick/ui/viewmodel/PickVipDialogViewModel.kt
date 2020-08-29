package com.will.play.pick.ui.viewmodel

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseDialogViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.pick.repository.PickRepository

/**
 * Desc:商品详情页vip对话框dialog
 *
 * Date: 2020-07-12 13:25
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class PickVipDialogViewModel(application: Application) : BaseDialogViewModel<PickRepository>(application) {

    /**
     * 左边按钮点击事件
     */
    val onLeftClick=BindingCommand<Any>(object : BindingAction {
        override fun call() {
            finish()
        }
    })

    /**
     * 右边按钮点击事件
     */
    val onRightClick=BindingCommand<Any>(object : BindingAction {
        override fun call() {
            ARouter.getInstance().build("/mine/vipdetail").navigation()
        }
    })
}