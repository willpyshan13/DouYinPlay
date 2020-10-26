package com.will.play.mine.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.entity.DouyinVideoLists
import com.will.play.mine.entity.TypeLists

class MineTalentListFilterHeaderImageItem(viewModel: MineTalentListViewModel,val headerItem: MineTalentListFilterHeaderItem,
                                          val data: TypeLists,val first:Boolean) :ItemViewModel<MineTalentListViewModel>(viewModel) {
    val showSelect = ObservableInt(if (first) View.VISIBLE else View.INVISIBLE)

    val onItemClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            headerItem.resetSelect()
            viewModel.typeId = data.id
            showSelect.set(View.VISIBLE)
        }
    })
}