package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的钱包
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineWalletViewModel(application: Application) : BaseViewModel<MineRepository>(application) {


    val mDataList = arrayListOf("收入")


    val viewPagerObservableList = ObservableArrayList<Any>()

    var viewPagerItemBinding = ItemBinding.of<Any> { itemBinding, _, item ->
        when (item) {
            is MineWalletWithdrawItemViewModel -> itemBinding.set(BR.viewModel, R.layout.mine_activity_wallet_pager_withdraw_layout)
            is MineWalletIncomeItemViewModel -> itemBinding.set(BR.viewModel, R.layout.mine_activity_wallet_pager_income_layout)
        }
    }


    override fun onCreate() {
        super.onCreate()
        viewPagerObservableList.add(MineWalletIncomeItemViewModel(this))
    }


}