package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.view.View
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MInePartnerActivity
import com.will.play.mine.ui.activity.MineWechatAuthActivity
import com.will.play.mine.ui.activity.MineWithDrawActivity
import com.will.play.mine.ui.activity.MineWithDrawHistoryActivity

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
class MineWithDrawModel(application: Application) :BaseViewModel<MineRepository>(application) {
    override fun onCreate() {
        super.onCreate()
        setTitleText("提现")
        withDrawRequest()
    }

    val onChangePartnerClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineWechatAuthActivity::class.java)
        }
    })

    fun onWithdrawHistoryClick() = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineWithDrawHistoryActivity::class.java)
        }
    })

    private fun withDrawRequest(){
        launch({
            model.getPointApply()
        })
    }
}