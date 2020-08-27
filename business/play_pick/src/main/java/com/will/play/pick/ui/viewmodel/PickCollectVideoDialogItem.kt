package com.will.play.pick.ui.viewmodel

import androidx.databinding.ObservableBoolean
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.pick.entity.DouyinVideoLists
import com.will.play.pick.entity.PickGoodTypeEntityItem

class PickCollectVideoDialogItem(viewModel: PickCollectDialogViewModel, val data: DouyinVideoLists) :ItemViewModel<PickCollectDialogViewModel>(viewModel) {
    val check = ObservableBoolean(false)
    val itemClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            viewModel.bindVideo(data)
        }

    })
}