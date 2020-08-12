package com.will.play.pick.ui.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.play.pick.entity.PickGoodTypeEntityItem

class PickTopItem(viewModel: PickViewModel,val data: PickGoodTypeEntityItem) :ItemViewModel<PickViewModel>(viewModel) {
}