package com.will.play.pick.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.entity.TaskLists
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:搜索页面item ViewModel
 *
 * Date: 2020-07-12 15:33
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class PickSearchItemViewModel(viewModel:PickSearchViewModel,val data: TaskLists) :ItemViewModel<PickSearchViewModel>(viewModel){

    val items = ObservableArrayList<PickSearchItemTagItemViewModel>()

    val itemBinding: ItemBinding<PickSearchItemTagItemViewModel> = ItemBinding.of(BR.viewModel, R.layout.item_pick_search_tag_item_layout)

    val onItemClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }

    })

    init {
        items.add(PickSearchItemTagItemViewModel("可领取视频"))
        items.add(PickSearchItemTagItemViewModel("可领取实物"))

    }

}

/**
 * Desc:搜索页面item的
 * <p>
 * Date: 2020-07-23
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * Author: zhuanghongzhan
 */
class PickSearchItemTagItemViewModel(val tagText: String)