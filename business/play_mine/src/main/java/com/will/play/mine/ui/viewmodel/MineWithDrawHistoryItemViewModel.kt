package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.entity.DataLists
import java.util.*

/**
 * Desc:提现记录
 * Date: 2020-09-29 22:19
 * @Author: pengyushan
 */
class MineWithDrawHistoryItemViewModel(viewModel:MineWithDrawHistoryModel,val data: DataLists):ItemViewModel<MineWithDrawHistoryModel>(viewModel) {
    val status = ObservableField("提现状态:${data.status_name}")

    val money = ObservableField("+${data.amount} 元")
}
