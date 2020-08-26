package com.will.play.pick.ui.viewmodel

import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.play.pick.entity.VideoLists

class PickCollectionItem(viewModel: BaseViewModel<*>,val data: VideoLists,val isCollect:Boolean) :ItemViewModel<BaseViewModel<*>>(viewModel) {
}