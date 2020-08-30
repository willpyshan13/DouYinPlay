package com.will.play.pick.ui.viewmodel

import android.os.Bundle
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.play.pick.entity.PickGoodTypeEntityItem
import com.will.play.pick.ui.activity.PickSearchActivity

class PickTopItem(viewModel: PickViewModel,val data: PickGoodTypeEntityItem) :ItemViewModel<PickViewModel>(viewModel) {
    val onItemClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            val bundle = Bundle().apply {
                putString(ConstantConfig.TYPE_ID,"${data.id}")
            }
            viewModel.startActivity(PickSearchActivity::class.java,bundle)
        }

    })
}