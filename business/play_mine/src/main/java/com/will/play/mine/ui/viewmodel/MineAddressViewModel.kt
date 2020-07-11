package com.will.play.mine.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.*

/**
 * Desc:我的地址页面
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineAddressViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    val userName = ObservableField("")
    val userPhone = ObservableField("")
    val userArea = ""
    val userAddress = ObservableField("")

    init {
        setTitleText(StringUtils.getStringResource(R.string.mine_address))
    }

    val onAddressClick = BindingCommand<Any>(object :BindingAction{
        override fun call() {

        }

    })
}