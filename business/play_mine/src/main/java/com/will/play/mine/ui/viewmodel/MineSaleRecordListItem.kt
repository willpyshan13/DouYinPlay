package com.will.play.mine.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.entity.MineTalentRecordDataListsEntity

/**
 * 绑定抖音  item
 */
class MineSaleRecordListItem(viewModel: MineSaleRecordViewModel,val data: MineTalentRecordDataListsEntity) : ItemViewModel<MineSaleRecordViewModel>(viewModel) {

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}