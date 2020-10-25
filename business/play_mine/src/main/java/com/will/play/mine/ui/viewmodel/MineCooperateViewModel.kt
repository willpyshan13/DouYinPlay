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

    val mDataList = arrayListOf("申请中","合作中","合作完成")

    val viewPagerObservableList = ObservableArrayList<Any>()

    var viewPagerItemBinding = ItemBinding.of<Any> { itemBinding, _, item ->
        when (item) {
            is MineCooperateListViewModel -> itemBinding.set(BR.viewModel, R.layout.mine_activity_cooperate_list)
        }
    }

    override fun onCreate() {
        super.onCreate()
        setTitleText("合作列表")
    }

    init {
        viewPagerObservableList.add(MineCooperateListViewModel(this,"1"))
        viewPagerObservableList.add(MineCooperateListViewModel(this,"2"))
        viewPagerObservableList.add(MineCooperateListViewModel(this,"3"))
    }

}