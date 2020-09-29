package com.will.play.mine.ui.viewmodel

import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand

/**
 * Desc:提现记录
 * Date: 2020-09-29 22:19
 * @Author: pengyushan
 */
class MineWithDrawHistoryItemViewModel(viewModel:MineWalletViewModel) {

    fun onWithdrawClick() = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }
    })
}
