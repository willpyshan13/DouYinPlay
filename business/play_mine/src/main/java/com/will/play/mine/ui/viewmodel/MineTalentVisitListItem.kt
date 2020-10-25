package com.will.play.mine.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand

/**
 * 更多item
 */
class MineTalentVisitListItem(viewModel: MineTalentViewModel, val imgUrl:String) : ItemViewModel<MineTalentViewModel>(viewModel) {
   val showMore = ObservableInt(if (imgUrl.isEmpty())View.VISIBLE else View.GONE)
    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}