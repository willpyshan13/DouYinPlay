package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.MineRespRecordEntity
import com.will.play.mine.entity.RecordDataLists
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineWithDrawActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的钱包提现itemViewModel
 * Date: 2020-09-09 22:19
 * @Author: zhuanghongzhan
 */
class MineWalletIncomeItemViewModel(val viewModel:MineWalletViewModel,val data: MineRespRecordEntity):BaseListViewModel<MineRepository,MineWalletIncomeDataItemViewModel>(viewModel.getApplication()) {

    val totalMoney = ObservableField("收入总额：${data.sum_type41}元")
    val totalMoneyCount = ObservableField("${data.count_type41}笔")
    fun onWithdrawClick() = BindingCommand<Any>(object :BindingAction{
        override fun call() {
            viewModel.startActivity(MineWithDrawActivity::class.java)
        }
    })

    init {
        loadInit()
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<MineWalletIncomeDataItemViewModel> {
        return object :DiffUtil.ItemCallback<MineWalletIncomeDataItemViewModel>(){
            override fun areItemsTheSame(oldItem: MineWalletIncomeDataItemViewModel, newItem: MineWalletIncomeDataItemViewModel): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: MineWalletIncomeDataItemViewModel, newItem: MineWalletIncomeDataItemViewModel): Boolean {
                return false
            }

        }
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<MineWalletIncomeDataItemViewModel> {
        return ItemBinding.of<MineWalletIncomeDataItemViewModel>(BR.viewModel, R.layout.mine_activity_wallet_income_data_item_layout)
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<MineWalletIncomeDataItemViewModel>) {
        viewModel.launch({
            val itemList = data.dataLists.map { MineWalletIncomeDataItemViewModel(viewModel,it) }
            items.addAll(itemList)
            loadCallback.onSuccess(itemList,pageIndex,1)
        })
    }

}

class MineWalletIncomeDataItemViewModel(viewModel:MineWalletViewModel,val data:RecordDataLists):ItemViewModel<MineWalletViewModel>(viewModel) {
    val fromText = ObservableField("来自 ${data.task_user_nickname} 的补贴")
    val money = ObservableField("+ ${data.amount} 元")
}

