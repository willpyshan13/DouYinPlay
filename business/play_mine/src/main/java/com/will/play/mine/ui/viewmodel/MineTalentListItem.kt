package com.will.play.mine.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.entity.HomeFilterDataList

/**
 * 绑定抖音  item
 */
class MineTalentListItem(viewModel: BaseViewModel<*>,val data:HomeFilterDataList) : ItemViewModel<BaseViewModel<*>>(viewModel) {

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}