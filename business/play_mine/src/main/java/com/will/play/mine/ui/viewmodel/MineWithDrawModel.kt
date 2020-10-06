package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.play.mine.repository.MineRepository
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
    val username = ObservableField("")
    val imageUrl = ObservableField("")
    val withDrawMoneyText = ObservableField("")

    val withDrawMoney = ObservableField("")

    private var userWithDrawMoneyMax = 0F

    override fun onCreate() {
        super.onCreate()
        setTitleText("提现")
        withDrawRequest()
    }

    val onChangePartnerClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            withDrawCheck()
        }
    })

    private fun withDrawCheck(){
        launch({
            model.getPointApply()
        })
    }

    private fun pointApply(){
        launch({
            model.getPointApplyAdd(withDrawMoney.get())
        })
    }

    val onWithDrawAll = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            withDrawMoney.set("$userWithDrawMoneyMax")
        }
    })

    fun onWithdrawHistoryClick() = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            startActivity(MineWithDrawHistoryActivity::class.java)
        }
    })

    private fun withDrawRequest(){
        launch({
            val data = model.getUserIndex()
            username.set(data.userInfo.username)
            imageUrl.set(data.userInfo.avatar)
            userWithDrawMoneyMax = data.userInfo.point.toFloat()
            withDrawMoneyText.set("可提现金额${data.userInfo.point}元")
        })
    }
}