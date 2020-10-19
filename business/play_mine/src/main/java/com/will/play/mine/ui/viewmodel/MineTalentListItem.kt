package com.will.play.mine.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand

/**
 * 绑定抖音  item
 */
class MineTalentListItem(viewModel: MineTalentListViewModel) : ItemViewModel<MineTalentListViewModel>(viewModel) {

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}