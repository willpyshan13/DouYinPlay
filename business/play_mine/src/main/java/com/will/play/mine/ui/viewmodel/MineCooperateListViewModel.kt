package com.will.play.mine.ui.viewmodel

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.MineRespRecordEntity
import com.will.play.mine.repository.MineRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:合作列表item
 * Date: 2020-10-22 22:19
 * @Author: pengyushan
 */
class MineCooperateListViewModel(val viewModel:MineWalletViewModel,val data: MineRespRecordEntity):BaseListViewModel<MineRepository,MineTalentListItem>(viewModel.getApplication()) {

    init {
        loadInit()
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<MineTalentListItem> {
        return object :DiffUtil.ItemCallback<MineTalentListItem>(){
            override fun areItemsTheSame(oldItem: MineTalentListItem, newItem: MineTalentListItem): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: MineTalentListItem, newItem: MineTalentListItem): Boolean {
                return false
            }

        }
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<MineTalentListItem> {
        return ItemBinding.of(BR.viewModel, R.layout.mine_activity_talent_item)
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<MineTalentListItem>) {
        viewModel.launch({
//            val itemList = data.dataLists.map { MineWalletIncomeDataItemViewModel(viewModel,it) }
//            items.addAll(itemList)
//            loadCallback.onSuccess(itemList,pageIndex,1)
        })
    }

}