package com.will.play.mine.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.entity.DataLists

/**
 * Desc:提现记录
 * Date: 2020-09-29 22:19
 * @Author: pengyushan
 */
class MineWithDrawHistoryItemViewModel(viewModel:MineWithDrawHistoryModel,data: DataLists):ItemViewModel<MineWithDrawHistoryModel>(viewModel) {

    fun onWithdrawClick() = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }
    })
}
