package com.will.play.pick.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.pick.entity.VideoLists

class PickCollectionItem(viewModel: PickCollectionVideoViewModel,val data: VideoLists,val isCollect:Boolean) :ItemViewModel<PickCollectionVideoViewModel>(viewModel) {
    val collectClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            viewModel.getDouyinVideoList(data)
        }
    })
}