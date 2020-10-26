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
class MineTalentListFilterItem(viewModel: MineTalentListViewModel, val data:List<FansLists>,val title:String) : ItemViewModel<MineTalentListViewModel>(viewModel) {

    /**
     * 选中的值
     */
    val trainModeFreeSelect = ObservableField<Set<Int>>()


    var selectPosition = 0

    val items = ObservableArrayList<MineTalentListFilterChoiceItem>()

    val itemBinding: ItemBinding<MineTalentListFilterChoiceItem> = ItemBinding.of(BR.viewModel, R.layout.mine_activity_talent_filter_choice_item)

    fun resetSelect(){
        trainModeFreeSelect.set(mutableSetOf(0))
    }

    init {
        trainModeFreeSelect.set(mutableSetOf(0))
    }

    val trainModeChangeAction = object : BindingConsumer<Set<Int>> {
        override fun call(t: Set<Int>) {
            if (t.isNotEmpty()) {
                selectPosition = t.first()
                setViewModelType()
            }
        }
    }

    private fun setViewModelType(){
        when(title){
            "达人级别" -> viewModel.levelId = items[selectPosition].data.id
            "平台" -> viewModel.platformId = items[selectPosition].data.id
            "性别" -> viewModel.genderId = items[selectPosition].data.id
            "粉丝量" -> viewModel.fansId = items[selectPosition].data.id
            else -> viewModel.authId = items[selectPosition].data.id
        }
    }



    init {
        val dataList = data.map { MineTalentListFilterChoiceItem(viewModel,it) }
        items.addAll(dataList)
    }
}