package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.view.View
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.utils.StringUtils
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.MinePayInfoEntity
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MInePartnerActivity
import com.will.play.pay.WillPay
import com.will.play.pay.wechat.wxpay.WXPay

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
class MineWechatAuthViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    val payClick = SingleLiveEvent<MinePayInfoEntity>()
    override fun onCreate() {
        super.onCreate()
        setTitleText("提现授权")
    }

    val onChangePartnerClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
           payClick.call()
        }

    })
}