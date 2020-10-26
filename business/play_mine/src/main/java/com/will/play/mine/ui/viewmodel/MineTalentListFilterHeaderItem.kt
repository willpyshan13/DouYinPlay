package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.binding.command.BindingConsumer
import com.will.play.base.constant.Constants
import com.will.play.base.entity.HomeFilterDataList
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.FansLists
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 绑定抖音  item
 */
class MineTalentListFilterHeaderItem(viewModel: MineTalentListViewModel, val data:List<FansLists>, val title:String) : ItemViewModel<MineTalentListViewModel>(viewModel) {

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}