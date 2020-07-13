package com.will.play.mine.ui.viewmodel

import android.app.Application
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineVipDetailActivity

/**
 * Desc:我的地址页面
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineVipViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    val vipLayout = MineVipLayoutItem(this)

    val onVipClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineVipDetailActivity::class.java)
        }

    })
}