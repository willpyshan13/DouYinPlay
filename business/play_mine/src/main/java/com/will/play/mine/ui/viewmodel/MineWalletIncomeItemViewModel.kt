package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.mine.R
import com.will.play.mine.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的钱包提现itemViewModel
 * Date: 2020-09-09 22:19
 * @Author: zhuanghongzhan
 */
class MineWalletIncomeItemViewModel(viewModel:MineWalletViewModel) {

    fun onWithdrawClick() = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }
    })

    val itemBinding = ItemBinding.of<MineWalletIncomeDataItemViewModel>(BR.viewModel, R.layout.mine_activity_wallet_income_data_item_layout)

    val items = ObservableArrayList<MineWalletIncomeDataItemViewModel>()


    init {
        repeat(10) {
            items.add(MineWalletIncomeDataItemViewModel(viewModel))
        }

    }

}

class MineWalletIncomeDataItemViewModel(viewModel:MineWalletViewModel) {

}

