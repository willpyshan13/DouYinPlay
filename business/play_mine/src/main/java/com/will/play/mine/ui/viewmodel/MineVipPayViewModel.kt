package com.will.play.mine.ui.viewmodel

import android.app.Application
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.launch
import com.will.play.mine.entity.MinePayInfoEntity
import com.will.play.mine.entity.UpgradeLists
import com.will.play.mine.repository.MineRepository

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
class MineVipPayViewModel(application: Application,val payMoney: UpgradeLists?) :BaseViewModel<MineRepository>(application) {
    val vipLayout = MineVipLayoutItem(this)
    val payClick = SingleLiveEvent<MinePayInfoEntity>()
    override fun onCreate() {
        super.onCreate()
        setTitleText("订单支付")
    }

    val onPayClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            launch({
                showDialog()
                val data = model.getOrder2Add("${payMoney?.id}")
                if (data!=null){
                    getOrderPayDetail(data.order_id)
                }

            },{
                dismissDialog()
            })
        }
    })

    private fun getOrderPayDetail(orderId:String){
        launch({
            val data = model.getOrder2Pay(orderId)
            payClick.value = data
            dismissDialog()
        },{
            dismissDialog()
        })
    }
}