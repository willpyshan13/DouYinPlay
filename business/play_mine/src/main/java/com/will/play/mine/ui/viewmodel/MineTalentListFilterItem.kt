package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.constant.Constants
import com.will.play.base.entity.HomeFilterDataList
import com.will.play.mine.R
import com.will.play.mine.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 绑定抖音  item
 */
class MineTalentListFilterItem(viewModel: BaseViewModel<*>, val data:HomeFilterDataList) : ItemViewModel<BaseViewModel<*>>(viewModel) {

    val items = ObservableArrayList<MineTalentListFilterChoiceItem>()

    val itemBinding: ItemBinding<MineTalentListFilterChoiceItem> = ItemBinding.of(BR.viewModel, R.layout.mine_activity_talent_filter_choice_item)

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            ARouter.getInstance().build("/mine/talentinfo")
                    .withString(Constants.COMMON_ID,"${data.id}")
                    .navigation()
        }
    })
}