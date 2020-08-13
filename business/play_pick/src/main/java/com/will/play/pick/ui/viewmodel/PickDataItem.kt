package com.will.play.pick.ui.viewmodel

import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.binding.command.BindingConsumer
import com.will.play.pick.entity.TaskLists
import com.will.play.pick.ui.activity.PickGoodsDetailActivity

class PickDataItem(viewModel: BaseViewModel<*>,val data: TaskLists) :ItemViewModel<BaseViewModel<*>>(viewModel) {
    val onItemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.startActivity(PickGoodsDetailActivity::class.java)
        }
    })
}