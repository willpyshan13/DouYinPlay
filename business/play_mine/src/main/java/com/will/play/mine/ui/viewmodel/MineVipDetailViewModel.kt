package com.will.play.mine.ui.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.collection.DiffObservableArrayList
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig.VIP_PAY_MONEY
import com.will.habit.extection.launch
import com.will.habit.utils.StringUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.mine.R
import com.will.play.mine.BR
import com.will.play.mine.repository.MineRepository
import com.will.play.mine.ui.activity.MineVipPayActivity
import me.tatarka.bindingcollectionadapter2.ItemBinding

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
class MineVipDetailViewModel(application: Application) :BaseViewModel<MineRepository>(application) {
    init {
        setTitleText(StringUtils.getStringResource(R.string.mine_vip_title))
    }

    val items = DiffObservableArrayList(object : DiffUtil.ItemCallback<MineVipDetailItem>() {
        override fun areItemsTheSame(oldItem: MineVipDetailItem, newItem: MineVipDetailItem): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: MineVipDetailItem, newItem: MineVipDetailItem): Boolean {
            return false
        }
    })


    fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is MineVipDetailItem -> binding.set(BR.viewModel, R.layout.mine_activity_vip_detail_item)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        getVipDetail()
    }

    private fun getVipDetail(){
        launch({
            val data = model.getUpgrade()
            val viewList = data?.upgradeLists?.map {MineVipDetailItem(this,it)  }.orEmpty()
            items.submit(viewList,false)
        })
    }
}