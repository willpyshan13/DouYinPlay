package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.launch
import com.will.habit.extection.parse
import com.will.habit.utils.SPUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.entity.MineUserInfo
import com.will.play.mine.repository.MineRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:我的合作
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineCooperateViewModel(application: Application) : BaseViewModel<MineRepository>(application) {


    val mDataList = arrayListOf("收入")

    val username = ObservableField("")
    val userHeader = ObservableField("")
    val userMoney = ObservableField("")
    val viewPagerObservableList = ObservableArrayList<Any>()

    var viewPagerItemBinding = ItemBinding.of<Any> { itemBinding, _, item ->
        when (item) {
            is MineWalletWithdrawItemViewModel -> itemBinding.set(BR.viewModel, R.layout.mine_activity_wallet_pager_withdraw_layout)
            is MineWalletIncomeItemViewModel -> itemBinding.set(BR.viewModel, R.layout.mine_activity_wallet_pager_income_layout)
        }
    }

    override fun onCreate() {
        super.onCreate()
        getRecord()
    }

    private fun getRecord(){
        launch({
            val userData = SPUtils.instance.getString(ConstantConfig.USER_INFO)?.parse<MineUserInfo>()

            username.set(userData?.userInfo?.username)
            userHeader.set(userData?.userInfo?.avatar)
            val data = model.getPointLog()
//            viewPagerObservableList.add(MineWalletIncomeItemViewModel(this,data))

        })
    }


}