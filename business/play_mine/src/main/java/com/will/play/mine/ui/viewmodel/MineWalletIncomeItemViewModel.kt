package com.will.play.mine.ui.viewmodel

import androidx.databinding.ObservableArrayList
import com.will.play.mine.R
import com.will.play.mine.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的钱包提现itemViewModel
 * Date: 2020-09-09 22:19
 * @Author: zhuanghongzhan
 */
class MineWalletIncomeItemViewModel {


    val itemBinding = ItemBinding.of<MineWalletIncomeDataItemViewModel>(BR.viewModel, R.layout.mine_activity_wallet_income_data_item_layout)

    val items = ObservableArrayList<MineWalletIncomeDataItemViewModel>()


    init {
        repeat(10) {
            items.add(MineWalletIncomeDataItemViewModel())
        }

    }

}

class MineWalletIncomeDataItemViewModel {

}

