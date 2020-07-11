package com.will.play.mine.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.ui.activity.MineInfoEditActivity

/**
 * @author will
 */
class MineHomeHeaderItem(viewModel:MineViewModel) :ItemViewModel<MineViewModel>(viewModel) {

    val onMineInfoEditClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.startActivity(MineInfoEditActivity::class.java)
        }
    })
}