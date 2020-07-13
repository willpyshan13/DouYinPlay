package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.view.View
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.utils.StringUtils
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MInePartnerActivity

/**
 * Desc:修改角色
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineChangeRoleViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    override fun onCreate() {
        super.onCreate()
        setTitleText(StringUtils.getStringResource(R.string.mine_change_role))
    }

    val onChangePartnerClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MInePartnerActivity::class.java)
        }

    })
}