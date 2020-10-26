package com.will.play.mine.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayList
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.TypeLists
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * 绑定抖音  item
 */
class MineTalentListFilterHeaderItem(viewModel: MineTalentListViewModel, val data:List<TypeLists>) : ItemViewModel<MineTalentListViewModel>(viewModel) {

    val typesBind = ItemBinding.of<Any> { itemBinding, _, item ->
        when (item) {
            is MineTalentListFilterHeaderImageItem -> itemBinding.set(BR.viewModel, R.layout.mine_talent_list_filter_header_image_item)
        }
    }

    val typeItems = ObservableArrayList<MineTalentListFilterHeaderImageItem>()

    init {
        val dataList = data.mapIndexed { index, typeLists -> MineTalentListFilterHeaderImageItem(viewModel,this,typeLists,index==0) }
        typeItems.addAll(dataList)
    }

    fun resetSelect(){
        for (i in typeItems.indices){
            typeItems[i].showSelect.set(View.GONE)
        }
    }

    fun clearSelect(){
        for (i in typeItems.indices){
            typeItems[i].showSelect.set(View.GONE)
        }
        typeItems[0].showSelect.set(View.VISIBLE)
    }

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {

        }
    })
}