package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import com.will.habit.base.BaseViewModel
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.repository.PickSearchRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:搜索页面viewModel
 *
 * Date: 2020-07-12
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: zhuanghongzhan
 */
class PickSearchViewModel(context: Application) : BaseViewModel<PickSearchRepository>(context) {


    val itemList = DiffObservableArrayList(object : DiffUtil.ItemCallback<PickSearchItemViewModel>() {
        override fun areItemsTheSame(oldItem: PickSearchItemViewModel, newItem: PickSearchItemViewModel): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: PickSearchItemViewModel, newItem: PickSearchItemViewModel): Boolean {
            return true
        }
    })

    val itemBinding = ItemBinding.of<PickSearchItemViewModel>(BR.viewModel, R.layout.item_pick_search_layout)


    init {
        val list = mutableListOf<PickSearchItemViewModel>()
        for (i in 1..5) {
            list.add(PickSearchItemViewModel())
        }

        itemList.submit(list, false)
    }

}
